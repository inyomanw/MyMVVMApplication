package com.example.core.Utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.core.model.ErrorResponse
import com.google.gson.Gson

object ConnectionType {
    const val TYPE_OTHERS = 0
    const val TYPE_WIFI = 1
    const val TYPE_MOBILE = 2
    const val TYPE_NOT_CONNECTED = 3
}

object HttpNetworkCode {
    const val HTTP_BAD_REQUEST = 400
    const val HTTP_UNAUTHORIZED = 401
    const val HTTP_UNPROCESS = 422
}

fun Context.isConnectionAvailable() = getConnectionType() != ConnectionType.TYPE_NOT_CONNECTED

fun Context.getConnectionType(): Int {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    activeNetwork?.let {
        return when (it.type) {
            ConnectivityManager.TYPE_WIFI -> ConnectionType.TYPE_WIFI
            ConnectivityManager.TYPE_MOBILE -> ConnectionType.TYPE_MOBILE
            else -> ConnectionType.TYPE_OTHERS
        }
    }
    return ConnectionType.TYPE_NOT_CONNECTED
}

fun handleErrorMessage(text: String) = Gson().fromJson(text, ErrorResponse::class.java)