package com.example.inyomanw.mymvvmapplication.newdeps.module

import android.annotation.SuppressLint
import com.example.inyomanw.mymvvmapplication.newdeps.ActivityScoped
import com.example.inyomanw.mymvvmapplication.ui.mvp.MainActivity
import com.example.inyomanw.mymvvmapplication.ui.leivamvvm.LeivaMVVMActivity
import com.example.inyomanw.mymvvmapplication.ui.mvvm.MainMVVMActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("ReplaceArrayOfWithLiteral", "unused")
@SuppressLint("UNUSED")
@Module
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainMVVMActivity(): MainMVVMActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindLeivaMVVMActivity(): LeivaMVVMActivity


}