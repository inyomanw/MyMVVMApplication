package com.example.core.model


data class BencanaResponse(
    val success : Boolean?,
    val listbencana : List<BencanaModel>?
)

data class BencanaModel(
    val id_bencana : String?,
    val judul_bencana : String?,
    val deskripsi_bencana : String?,
    val tumnel : String?,
    val nomer_rekening : String?,
    val nama_penggalangdana : String?,
    val total_donasi : String?
)

