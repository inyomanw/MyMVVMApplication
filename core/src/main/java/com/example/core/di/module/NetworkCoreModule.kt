package com.example.core.di.module

import com.example.core.BuildConfig
import com.example.core.api.ApiCoreInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkCoreModule{

        @Provides
        @Singleton
        @Named("Retrofit-Core")
        fun providesCoreRetrofit(okHttpClient: OkHttpClient,
                             gson: Gson
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL_CORE)
                .build()
        }

        @Provides
        @Singleton
        fun providesCoreApiService(@Named("Retrofit-Core") retrofit: Retrofit): ApiCoreInterface {
            return retrofit.create(ApiCoreInterface::class.java)
        }
}