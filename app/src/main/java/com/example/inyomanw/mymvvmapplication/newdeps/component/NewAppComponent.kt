package com.example.inyomanw.mymvvmapplication.newdeps.component

import android.app.Application
import com.example.core.di.module.CoreActivityBuilder
import com.example.core.di.module.CoreModule
import com.example.core.di.module.NetworkCoreModule
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
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class,
        CoreModule::class,
        CoreActivityBuilder::class,
        NetworkCoreModule::class
    )
)
interface NewAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(myApp: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun network(networkModule: NetworkModule): Builder

        fun coreModule(coreModule: CoreModule) : Builder

        fun coreNetwork(networkCoreModule: NetworkCoreModule) : Builder

        fun build(): NewAppComponent
    }
}