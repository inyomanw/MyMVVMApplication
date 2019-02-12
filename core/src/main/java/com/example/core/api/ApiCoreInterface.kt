package com.example.core.api

import com.example.core.model.BencanaResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiCoreInterface{
    @GET("List_bencana")
    fun getBencana() : Observable<BencanaResponse>

    @GET("List_bencana")
    fun getResponseBencana() : Observable<Response<BencanaResponse>>
}