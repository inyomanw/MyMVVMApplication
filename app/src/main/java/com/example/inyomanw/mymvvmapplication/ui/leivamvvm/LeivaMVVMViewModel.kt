package com.example.inyomanw.mymvvmapplication.ui.leivamvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.inyomanw.mymvvmapplication.base.BaseViewModel
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.inyomanw.mymvvmapplication.data.repository.BarangRepo
import com.example.inyomanw.mymvvmapplication.utils.ScreenState
import javax.inject.Inject

class LeivaMVVMViewModel @Inject constructor(val repo: BarangRepo) : BaseViewModel() {

    private lateinit var _mainState: MutableLiveData<ScreenState<LeivaMVVMState>>

    val mainState: LiveData<ScreenState<LeivaMVVMState>>
        get() {
            if (!::_mainState.isInitialized) {
                _mainState = MutableLiveData()
                _mainState.value = ScreenState.Loading
                getBarangs()
            }
            return _mainState
        }

    private fun getBarangs(){
        compositeDisposable.add(repo.getBarangs(
            response = {
                it?.let {
                    //                    this::onSuccess
                    _mainState.value = ScreenState.Render(LeivaMVVMState.getListBarang(it))
                }
            },
            error = {
                //                this::onError
                _mainState.value = ScreenState.Render(LeivaMVVMState.showMessage(it.localizedMessage))

            }
        ))
    }


}


class LeivaViewModelFactory @Inject constructor(val repo: BarangRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LeivaMVVMViewModel(repo) as T
    }
}