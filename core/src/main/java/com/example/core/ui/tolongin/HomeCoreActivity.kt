package com.example.core.ui.tolongin

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.core.BuildConfig
import com.example.core.R
import com.example.core.Utils.*
import com.example.core.model.BencanaModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home_core.*
import kotlinx.android.synthetic.main.viewholder_bencana.view.*
import javax.inject.Inject

class HomeCoreActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private var listBencana: MutableList<BencanaModel> = mutableListOf()

    private val bencanaAdapter by lazy {
        GeneralRecyclerViewAdapter(R.layout.viewholder_bencana, listBencana, { model, _, _ ->
        }, { model, view ->
            view.iv_tumbnail.loadImage(
                this@HomeCoreActivity,
//                    BuildConfig.BASE_URL_CORE+"images/Bencana/"+model.tumnel
                "${BuildConfig.BASE_URL_CORE}images/Bencana/${model.tumnel}"
            )
            view.judul_bencana.text = model.judul_bencana
            view.tv_donation.text = model.total_donasi?.let { setCurrency(it.toDouble()) }

        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_core)
        observerItem()

        with(rv_bencana) {
            layoutManager = LinearLayoutManager(this@HomeCoreActivity)
            rv_bencana.adapter = bencanaAdapter
        }
        viewModel.getResponseBencana()
    }

    private fun observerItem() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    private val stateObserver = Observer<HomeState> { state ->
        when (state) {
            is DefaultState -> {
                progress_bar.gone()
                listBencana.addAll(state.getBencana)
                bencanaAdapter.update(listBencana)
            }
            is OnErrorState -> {
                progress_bar.gone()
                state.error?.let {
                    handleError(it)
                }.let {
                    it?.let { msg -> showSnackBar(constraintlayout, msg) }
                }
//                this@HomeCoreActivity.showShortToast("error ${state.error}")
            }
            is OnLoadingState -> {
                listBencana = state.getBencana.toMutableList()
            }
        }
    }
}
