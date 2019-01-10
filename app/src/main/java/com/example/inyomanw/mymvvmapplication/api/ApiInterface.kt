package com.example.inyomanw.mymvvmapplication.api

import com.example.inyomanw.mymvvmapplication.data.models.BarangResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("Barang")
    fun getBarang(): Observable<Response<BarangResponse>>

    @GET("Barang")
    fun getListBarang(): Observable<BarangResponse>

}