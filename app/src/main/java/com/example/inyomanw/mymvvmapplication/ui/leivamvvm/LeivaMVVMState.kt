package com.example.inyomanw.mymvvmapplication.ui.leivamvvm

import com.example.inyomanw.mymvvmapplication.data.models.Barang

sealed class LeivaMVVMState{
    class getListBarang(val barangs : List<Barang>) : LeivaMVVMState()
    class showMessage(val message : String) : LeivaMVVMState()
}