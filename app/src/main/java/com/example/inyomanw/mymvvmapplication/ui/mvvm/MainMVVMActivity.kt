package com.example.inyomanw.mymvvmapplication.ui.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.inyomanw.mymvvmapplication.R
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.inyomanw.mymvvmapplication.utils.GeneralRecyclerViewAdapter
import com.example.inyomanw.mymvvmapplication.utils.gone
import com.example.inyomanw.mymvvmapplication.utils.loadImage
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_mvvm.*
import kotlinx.android.synthetic.main.item_barang.view.*
import javax.inject.Inject

class MainMVVMActivity : DaggerAppCompatActivity() {

    private val TAG : String = "logv" + MainMVVMActivity::class.java.simpleName
    @Inject
    lateinit var viewModel: MainViewModel

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
                    view.imvBarang.loadImage(this@MainMVVMActivity, "https://inyomanw.com/WarungSepatu/"+gambars[0].gambar)
                }

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvvm)
        observerItem()

        viewModel.getBarangs()

        rv_barang.apply {
            layoutManager = LinearLayoutManager(this@MainMVVMActivity)
            rv_barang.adapter = classAdapter

        }
//        rv_barang.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun observerItem(){
        viewModel.stateLiveData.observe(this,stateObserver)
    }

    private val stateObserver = Observer<MainState> {state ->
        when(state){
            is DefaultState -> {
                progress_bar.gone()
                Log.d(TAG,"DefaultState")
                barangList = state.fetchBarang.toMutableList()
            }
            is OnErrorState -> {
                progress_bar.gone()
                Log.d(TAG,"ErrorState")
                Toast.makeText(this, "error ${state.message}", Toast.LENGTH_SHORT).show()
            }
            is OnLoadingState ->{
                barangList = state.fetchBarang.toMutableList()
            }
        }
    }

}
