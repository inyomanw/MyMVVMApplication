package com.example.inyomanw.mymvvmapplication.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        initLayout()
    }

    protected abstract fun initInjection()
    protected abstract fun initLayout()

}