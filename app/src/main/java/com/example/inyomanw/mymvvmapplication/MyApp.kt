package com.example.inyomanw.mymvvmapplication

import com.example.core.di.module.CoreModule
import com.example.core.di.module.NetworkCoreModule
import com.example.inyomanw.mymvvmapplication.newdeps.component.DaggerNewAppComponent
import com.example.inyomanw.mymvvmapplication.newdeps.component.NewAppComponent
import com.example.inyomanw.mymvvmapplication.newdeps.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val newAppComponent: NewAppComponent by lazy {
            DaggerNewAppComponent.builder()
                .application(this)
                .network(NetworkModule())
                .coreModule(CoreModule())
                .coreNetwork(NetworkCoreModule())
                .build()
        }
        return newAppComponent
    }
}