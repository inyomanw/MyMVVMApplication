package com.example.inyomanw.mymvvmapplication.ui.mvvm

import com.example.inyomanw.mymvvmapplication.data.models.Barang

sealed class MainState {
    abstract val fetchBarang : List<Barang>
}

data class DefaultState(override val fetchBarang: List<Barang>) : MainState()
data class OnLoadingState(override val fetchBarang: List<Barang>) : MainState()
data class  OnErrorState(val message: String, override val fetchBarang: List<Barang>) : MainState()