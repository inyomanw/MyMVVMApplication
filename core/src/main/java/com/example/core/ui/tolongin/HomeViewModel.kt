package com.example.core.ui.tolongin

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.core.Utils.HttpNetworkCode
import com.example.core.Utils.handleErrorMessage
import com.example.core.api.CoreRepo
import com.example.core.model.BencanaModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repo: CoreRepo) : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    val stateLiveData = MutableLiveData<HomeState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getBencana() {
        compositeDisposable.add(repo.getBencana(
            response = { response ->
                response?.let {
                    onSuccess(it)
                }
            },
            error = {
                onError(it)
            }
        ))
    }

    fun getResponseBencana() {
        compositeDisposable.add(repo.getResponseBencana({
            when {
                it.isSuccessful -> {
                    it.body()?.let { result ->
                        onSuccessResponse(result.listbencana)
                    }
                }
                it.code()== HttpNetworkCode.HTTP_UNPROCESS -> {
                    it.errorBody()?.string()?.let { errorBody ->
                        val msg = handleErrorMessage(errorBody)
                        onError(msg.errors[0])
                    }
                }
                else -> {
                    onError(it.code())
                }
            }
        }, {
            onError(it)
        }))
    }

    init {
        stateLiveData.value = OnLoadingState(emptyList())
    }

    private fun onGetCurrentBencana() = stateLiveData.value?.getBencana ?: emptyList()

    private fun onSuccess(bencana: List<BencanaModel>) {
        val currentBencana = onGetCurrentBencana().toMutableList()
        currentBencana.addAll(bencana)
        stateLiveData.value = DefaultState(currentBencana)
    }

    private fun onSuccessResponse(bencana: List<BencanaModel>?) {
        val currentBencana = onGetCurrentBencana().toMutableList()
        bencana?.let { currentBencana.addAll(it) }
        stateLiveData.value = DefaultState(currentBencana)
    }

    private fun onError(error: Any?) {
        stateLiveData.value = OnErrorState(error, onGetCurrentBencana())
    }


}