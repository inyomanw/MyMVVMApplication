package com.example.core.api

import com.example.core.Utils.uisubscribe
import com.example.core.model.BencanaModel
import com.example.core.model.BencanaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class CoreRepo @Inject constructor(val apiCoreInterface: ApiCoreInterface){

    fun getBencana(response: (List<BencanaModel>?) -> Unit, error: (Throwable) -> Unit ) : Disposable{
        return apiCoreInterface.getBencana()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribe(
                {response(it.listbencana)},
                {error(it)}
            )
    }

    fun getResponseBencana(onNext : (Response<BencanaResponse>) -> Unit,
                           onError : (Throwable) -> Unit) = apiCoreInterface
        .getResponseBencana()
        .uisubscribe(onNext,onError)
}