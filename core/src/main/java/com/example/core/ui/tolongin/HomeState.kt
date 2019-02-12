package com.example.core.ui.tolongin

import com.example.core.model.BencanaModel

sealed class HomeState {
    abstract val getBencana : List<BencanaModel>
}

data class DefaultState(override val getBencana : List<BencanaModel>) : HomeState()
data class OnLoadingState(override val getBencana: List<BencanaModel>) : HomeState()
data class OnErrorState(val error: Any?, override val getBencana: List<BencanaModel>) : HomeState()

