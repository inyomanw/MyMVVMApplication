package com.example.inyomanw.mymvvmapplication.deps.module

import com.example.inyomanw.mymvvmapplication.api.ApiInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//class NetworkModule {
//
//    @Provides
//    @Singleton
//    fun providesGson(): Gson {
//        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//            .registerTypeAdapter(Date::class.java, DateTypeAdapter()).create()
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun providesRetrofit(okHttpClient: OkHttpClient,
//                         gson: Gson
//    ): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(okHttpClient)
//            .baseUrl("https://inyomanw.com/WarungSepatu/api/v1/")
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun providesApiService(retrofit: Retrofit): ApiInterface {
//        return retrofit.create(ApiInterface::class.java)
//    }
//
//}