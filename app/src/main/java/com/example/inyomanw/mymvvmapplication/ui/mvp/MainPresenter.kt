package com.example.inyomanw.mymvvmapplication.ui.mvp

import com.example.inyomanw.mymvvmapplication.base.BasePresenter
import com.example.inyomanw.mymvvmapplication.data.repository.BarangRepo
import com.example.inyomanw.mymvvmapplication.newdeps.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainPresenter @Inject constructor(private val barangRepo: BarangRepo) : BasePresenter<MainView>() {



    fun getAllBarangs(){
        view?.updateProgressLoading(true)
        disposables.add(barangRepo.getBarangs(
            response = {
                it?.let { response ->
                    view?.displayListBarangs(it)
                    view?.updateProgressLoading(false)
                }

            },
            error = {
                view?.displayError(it.localizedMessage)
                view?.updateProgressLoading(false)
            }
        ))
    }


}