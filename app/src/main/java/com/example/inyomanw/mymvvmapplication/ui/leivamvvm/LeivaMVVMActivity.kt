package com.example.inyomanw.mymvvmapplication.ui.leivamvvm

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.inyomanw.mymvvmapplication.R
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.inyomanw.mymvvmapplication.utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_leiva_mvvm.*
import kotlinx.android.synthetic.main.item_barang.view.*
import javax.inject.Inject

class LeivaMVVMActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: LeivaMVVMViewModel

    @Inject
    lateinit var factory: LeivaViewModelFactory

    private var barangList: MutableList<Barang> = mutableListOf()


    private val classAdapter by lazy {
        GeneralRecyclerViewAdapter(R.layout.item_barang, barangList,
            { barang, _, _ ->

            },
            { barang, view ->
                with(barang) {
                    view.tvNamaBarang.text = namabarang
                    view.tvDeskripsi.text = deskripsi
                    view.tvMerk.text = merk
                    view.tvHarga.text = harga
                    view.imvBarang.loadImage(this@LeivaMVVMActivity, "https://inyomanw.com/WarungSepatu/"+gambars[0].gambar)
                }

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leiva_mvvm)
        rv_barang.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        viewModel = ViewModelProviders.of(this).get(LeivaMVVMViewModel::class.java)
        viewModel = ViewModelProviders.of(this,factory)[LeivaMVVMViewModel::class.java]
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(screenState: ScreenState<LeivaMVVMState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: LeivaMVVMState) {
        hideProgress()
        when (renderState) {
            is LeivaMVVMState.getListBarang-> setItems(renderState.barangs)
            is LeivaMVVMState.showMessage -> showMessage(renderState.message)
        }
    }

    private fun showProgress() {
        progress_bar.visible()
        rv_barang.gone()
    }

    private fun hideProgress() {
        progress_bar.gone()
        rv_barang.visible()
    }

    private fun setItems(barangs: List<Barang>) {
        barangList = barangs.toMutableList()
        rv_barang.adapter = classAdapter

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
