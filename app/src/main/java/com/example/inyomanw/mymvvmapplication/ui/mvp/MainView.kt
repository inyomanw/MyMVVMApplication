package com.example.inyomanw.mymvvmapplication.ui.mvp

import com.example.inyomanw.mymvvmapplication.base.BaseView
import com.example.inyomanw.mymvvmapplication.data.models.Barang

interface MainView : BaseView {
    fun displayListBarangs(barangs: List<Barang>)
    fun displayError(message: String?)
}