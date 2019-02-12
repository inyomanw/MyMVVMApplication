package com.example.core.di.module

import android.annotation.SuppressLint
import com.example.core.di.ActivityScoped
import com.example.core.ui.tolongin.HomeCoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class CoreActivityBuilder{
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindHomeCoreActivity() : HomeCoreActivity
}