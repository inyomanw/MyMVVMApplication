package com.example.inyomanw.mymvvmapplication.ui.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.inyomanw.mymvvmapplication.R
import com.example.inyomanw.mymvvmapplication.data.models.Barang
import com.example.core.Utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_barang.view.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter
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
                    view.imvBarang.loadImage(this@MainActivity, "https://inyomanw.com/WarungSepatu/"+gambars[0].gambar)
                }

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        rv_barang.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        presenter.attach(this)
        presenter.getAllBarangs()

    }
//
//    override fun initInjection() {
//
//        val activityComponent: ActivityComponent = (applicationContext as MyApp)
//            .appComponent
//            .activityComponent()
//            .build()
//
//        activityComponent.inject(this)
//    }
//
//    override fun initLayout() {
//        setContentView(R.layout.activity_main)
//        rv_barang.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        presenter.attach(this)
//        presenter.getAllBarangs()
//
//    }

    override fun displayListBarangs(barangs: List<Barang>) {
        barangList = barangs.toMutableList()
        rv_barang.adapter = classAdapter
    }

    override fun displayError(message: String?) {
    }

    override fun updateProgressLoading(visible: Boolean) {
        progress_bar.visibility = if (visible) View.VISIBLE else View.GONE

    }

}
