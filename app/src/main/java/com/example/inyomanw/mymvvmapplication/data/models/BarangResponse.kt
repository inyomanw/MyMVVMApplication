package com.example.inyomanw.mymvvmapplication.data.models

data class BarangResponse(
    val barangs: List<Barang>
)

data class Barang(
    val deskripsi: String,
    val gambars: List<Gambar>,
    val harga: String,
    val idbarang: String,
    val komens: List<Any>,
    val merk: String,
    val namabarang: String,
    val sizebarangs: List<Sizebarang>,
    val terjual: String,
    val ulasans: List<Any>
)

data class Sizebarang(
    val idbarang: String,
    val idsizebarang: String,
    val size: String,
    val stock: String
)

data class Gambar(
    val gambar: String,
    val idbarang: String,
    val idgambar: String
)