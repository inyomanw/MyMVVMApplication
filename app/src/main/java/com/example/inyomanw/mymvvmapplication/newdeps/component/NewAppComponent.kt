package com.example.inyomanw.mymvvmapplication.newdeps.component

import android.app.Application
import com.example.inyomanw.mymvvmapplication.MyApp
import com.example.inyomanw.mymvvmapplication.newdeps.module.ActivityBuilder
import com.example.inyomanw.mymvvmapplication.newdeps.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, ActivityBuilder::class, NetworkModule::class))
interface NewAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(myApp: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) :Builder
        fun network(networkModule: NetworkModule) :Builder

        fun build() : NewAppComponent
    }
}