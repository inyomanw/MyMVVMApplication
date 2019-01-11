package com.example.inyomanw.mymvvmapplication.ui.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.inyomanw.mymvvmapplication.R
import com.example.inyomanw.mymvvmapplication.ui.fragment.ui.barang.BarangFragment

class MainFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BarangFragment.newInstance())
                .commitNow()
        }
    }

}
