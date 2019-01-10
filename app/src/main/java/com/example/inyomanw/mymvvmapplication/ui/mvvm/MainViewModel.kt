package com.example.inyomanw.mymvvmapplication.ui.mvvm

import android.arch.lifecycle.MutableLiveData
import com.example.inyomanw.mymvvmapplication.base.BaseViewModel
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.inyomanw.mymvvmapplication.data.repository.BarangRepo
import javax.inject.Inject

class MainViewModel @Inject constructor(val repo: BarangRepo) : BaseViewModel(){

    val stateLiveData = MutableLiveData<MainState>()

    init{
        stateLiveData.value = OnLoadingState(emptyList())
    }


    fun getBarangs(){
        compositeDisposable.add(repo.getBarangs(
            response = {
                it?.let {
//                    this::onSuccess
                    onSuccess(it)
                }
            },
            error = {
//                this::onError
                onError(it)
            }
        ))
    }

    fun onRestore(){
        stateLiveData.value = DefaultState(onGetCurrentBarang())
    }

    private fun onSuccess(barangs: List<Barang>){
        val currentBarangs = onGetCurrentBarang().toMutableList()
        currentBarangs.addAll(barangs)
        stateLiveData.value = DefaultState(currentBarangs)
    }
    private fun onError(throwable: Throwable){
        stateLiveData.value = OnErrorState(throwable.localizedMessage,onGetCurrentBarang())
    }


    private fun onGetCurrentBarang() = stateLiveData.value?.fetchBarang ?: emptyList()


}