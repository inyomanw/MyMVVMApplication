package com.example.inyomanw.mymvvmapplication.data.repository

import com.example.inyomanw.mymvvmapplication.api.ApiInterface
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BarangRepo @Inject constructor(val apiInterface: ApiInterface) {

    fun getBarangs(response: (List<Barang>?) -> Unit,error: (Throwable) ->Unit) :Disposable{
        return apiInterface.getListBarang()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribe(
                {response(it.barangs)},
                {error(it)}
            )
    }

}