package com.example.pelatihankode.data.local

data class RiwayatEntity(

    val id: Long = 0,

    val siswaId: Long,

    val tanggal: String,

    val skor: Int,

    val benar: Int,

    val salah: Int,

    val bpm: Int?,

    val spo2: Int?,

    val kondisi: String?
)