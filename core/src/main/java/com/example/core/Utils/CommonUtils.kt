package com.example.core.Utils

import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.core.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.SocketTimeoutException
import java.text.NumberFormat
import java.util.*

object ConstantUtils {
    const val SNACKBAR_DURATION = 4000
    const val SUBSTRACT_STR_TO_MILLIS = 25200000
    private const val SERVER_ERROR_BASED = 500
    private const val SERVER_ERROR_TOP = 599
    val RANGE_SERVER_ERROR_CODE = SERVER_ERROR_BASED..SERVER_ERROR_TOP
    const val IMAGE_COMPRESSED_QUALITY = 100
    const val MIN_TEXT_LENGTH_ALLOWED = 10
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun setCurrency(double: Double): String {
    val localeID = Locale("in", "ID")
    val formatRP = NumberFormat.getCurrencyInstance(localeID)

    return formatRP.format(double).toString()
}

fun Context.showShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.buildAlertDialog(
    title: String = "title",
    message: String = "",
    yesButton: String = "Yes",
    noButton: String = "No",
    positiveAction: (DialogInterface) -> Unit = {},
    negativeAction: (DialogInterface) -> Unit = {}
): AlertDialog {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)

    if (yesButton.isNotEmpty()) {
        builder.setPositiveButton(yesButton) { dialog, _ ->
            positiveAction.invoke(dialog)
            dialog.dismiss()
        }
    }

    if (noButton.isNotEmpty()) {
        builder.setNegativeButton(noButton) { dialog, _ ->
            negativeAction.invoke(dialog)
            dialog.dismiss()
        }
    }

    return builder.create()
}

fun Context.showSnackBar(view: View, text: String, actionText: Int = android.R.string.ok,
                         duration: Int = Snackbar.LENGTH_INDEFINITE,
                         textColor: Int = ContextCompat.getColor(this, R.color.red),
                         onActionClick: () -> Unit = {}, dismissEvent: () -> Unit = {}) {
    val snackbar = Snackbar.make(view, text, duration)
    snackbar.setAction(actionText) {
        onActionClick.invoke()
        snackbar.dismiss()
    }
    snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.darkGrey))
    snackbar.duration = ConstantUtils.SNACKBAR_DURATION
    snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).setTextColor(textColor)
    snackbar.show()
    snackbar.addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            dismissEvent.invoke()
        }
    })
}

fun Context.handleError(error: Any): String {
    when (error) {
        is Int -> {
            return when (error) {
                in ConstantUtils.RANGE_SERVER_ERROR_CODE -> "Server error please check again later"
                else -> "Something when wrong"
            }
        }
        is Throwable -> {
            return when (error) {
                is IOException -> "Check your connection"
                is UnknownError -> "Something when wrong"
                is SocketTimeoutException -> "Something when wrong"
                else -> "Something when wrong"
            }
        }
    }
    return error as String
}


fun <T> Observable<T>.transform(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
        .onErrorResumeNext(Function { Observable.error { it } })
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.uisubscribe(
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onCompleted: () -> Unit = {}
): Disposable {
    return this.transform().toFlowable(BackpressureStrategy.BUFFER)
        .subscribe({
            onNext(it)
        },{
            onError(it)
        },{
            onCompleted.invoke()
        })
}