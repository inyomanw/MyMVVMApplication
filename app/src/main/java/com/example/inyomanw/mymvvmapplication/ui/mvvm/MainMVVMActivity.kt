package com.example.inyomanw.mymvvmapplication.ui.mvvm

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.core.Utils.*
import com.example.inyomanw.mymvvmapplication.R
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.core.ui.FlexibleSpace
import com.example.core.ui.tolongin.HomeCoreActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_mvvm.*
import kotlinx.android.synthetic.main.item_barang.view.*
import javax.inject.Inject


class MainMVVMActivity : DaggerAppCompatActivity() {

    private val TAG: String = "logv" + MainMVVMActivity::class.java.simpleName
    @Inject
    lateinit var viewModel: MainViewModel

    private var barangList: MutableList<Barang> = mutableListOf()


    private val classAdapter by lazy {
        GeneralRecyclerViewAdapter(R.layout.item_barang, barangList,
            { barang, _, _ ->
                Toast.makeText(this@MainMVVMActivity, barang.namabarang, Toast.LENGTH_SHORT).show()
            },
            { barang, view ->
                with(barang) {
                    view.tvNamaBarang.text = namabarang
                    view.tvDeskripsi.text = deskripsi
                    view.tvMerk.text = merk
                    view.tvHarga.text = harga
                    view.imvBarang.loadImage(
                        this@MainMVVMActivity,
                        "https://inyomanw.com/WarungSepatu/" + gambars[0].gambar
                    )
                }

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvvm)
        observerItem()

        with(rv_barang) {
            layoutManager = LinearLayoutManager(this@MainMVVMActivity)
            rv_barang.adapter = classAdapter

        }
        viewModel.getBarangs()
        fab.setOnClickListener {
            buildAlertDialog(message = "Apakah Anda yakin bosku?",
                positiveAction = {
                    if (isConnectionAvailable()){
                        startActivity(Intent(this@MainMVVMActivity,HomeCoreActivity::class.java))
                    }else{
                        showSnackBar(constraintlayout,"Check Your Connection")
                    }
                }).show()
//            startActivity(Intent(this@MainMVVMActivity,HomeCoreActivity::class.java))
        }
    }

    private fun observerItem() {
        viewModel.stateLiveData.observe(this, stateObserver)

    }

    private val stateObserver = Observer<MainState> { state ->
        when (state) {
            is DefaultState -> {
                progress_bar.gone()
                barangList.addAll(state.fetchBarang)
//                barangList.forEachIndexed { index, barang ->
//                    Log.d(TAG,"no $index nama barang : "+ barang.namabarang)
//                }
//                classAdapter.notifyDataSetChanged()
                classAdapter.update(barangList)
            }
            is OnErrorState -> {
                progress_bar.gone()
                Toast.makeText(this, "error ${state.message}", Toast.LENGTH_SHORT).show()
            }
            is OnLoadingState -> {
                barangList = state.fetchBarang.toMutableList()
            }
        }
    }

}
