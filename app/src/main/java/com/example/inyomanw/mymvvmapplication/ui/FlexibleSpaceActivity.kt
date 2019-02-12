package com.example.inyomanw.mymvvmapplication.ui

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.example.inyomanw.mymvvmapplication.R
import kotlinx.android.synthetic.main.activity_flexible_space.*

class FlexibleSpaceActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private val PERCENTAGE_TO_SHOW_IMAGE = 20
    private var mMaxScrollSize: Int = 0
    private var mIsImageHidden: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_space_core)

        appbar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange()

        val currentScrollPercentage = Math.abs(i) * 100 / mMaxScrollSize

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true
                collapsing.title = "flexible title"
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false
                collapsing.title = ""
            }
        }
    }



}
