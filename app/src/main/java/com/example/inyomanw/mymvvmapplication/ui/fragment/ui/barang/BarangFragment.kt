package com.example.inyomanw.mymvvmapplication.ui.fragment.ui.barang

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inyomanw.mymvvmapplication.R

class BarangFragment : Fragment() {

    companion object {
        fun newInstance() = BarangFragment()
    }

    private lateinit var viewModel: BarangViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.barang_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BarangViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
