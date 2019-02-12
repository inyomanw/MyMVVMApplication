package com.example.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule{

    @Provides
    @Singleton
    fun providesContext(application: Application): Context{
        return application
    }
}