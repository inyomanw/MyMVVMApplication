package com.example.core.ui

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.example.core.R
import kotlinx.android.synthetic.main.activity_flexible_space_core.*

class FlexibleSpace : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener{


    private val PERCENTAGE_TO_SHOW_IMAGE = 20
    private var mMaxScrollSize: Int = 0
    private var mIsImageHidden: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_space_core)
        appbarcore.addOnOffsetChangedListener(this)
    }

        override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
            if (mMaxScrollSize == 0)
                mMaxScrollSize = appBarLayout.getTotalScrollRange()

            val currentScrollPercentage = Math.abs(i) * 100 / mMaxScrollSize

            if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
                if (!mIsImageHidden) {
                    mIsImageHidden = true
                    collapsingcore.title = "core"
                }
            }

            if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
                if (mIsImageHidden) {
                    mIsImageHidden = false
                    collapsingcore.title = ""
                }
            }
        }

}
