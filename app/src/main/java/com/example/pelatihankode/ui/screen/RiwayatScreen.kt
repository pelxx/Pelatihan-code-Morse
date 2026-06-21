package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pelatihankode.data.local.AppDatabase
import com.example.pelatihankode.data.local.RiwayatEntity
import com.example.pelatihankode.data.local.SiswaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun RiwayatScreen(
    siswa: SiswaEntity?,
    siswaId: Long
) {

    val context = LocalContext.current
    val database = remember {
        AppDatabase.getDatabase(context)
    }

    var riwayatList by remember {
        mutableStateOf<List<RiwayatEntity>>(emptyList())
    }
    var selectedSiswa by remember {
        mutableStateOf(siswa)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(siswaId, siswa) {

        isLoading = true
        selectedSiswa = siswa

        val result = withContext(Dispatchers.IO) {
            val siswaData = siswa ?: database
                .siswaDao()
                .getSiswaById(
                    siswaId
                )

            val riwayatData = database
                .riwayatDao()
                .getRiwayatBySiswa(
                    siswaId
                )

            siswaData to riwayatData
        }

        selectedSiswa = result.first
        riwayatList = result.second
        isLoading = false
    }

    if (isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }

        return
    }

    if (riwayatList.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Belum ada riwayat"
            )
        }

    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = riwayatList,
                key = {
                    it.id
                }
            ) { item ->

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            selectedSiswa?.let {

                                Text(
                                    text = it.nama,
                                    fontSize = 22.sp
                                )

                                Text(
                                    text = "Umur : ${it.umur} Tahun"
                                )

                                Text(
                                    text = "Kelas : ${it.kelas}"
                                )
                            }

                            Text(
                                text = item.tanggal,
                                fontSize = 16.sp
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )
                            Text(
                                text = "BPM : ${item.bpm ?: "-"}"
                            )

                            Text(
                                text = "SpO2 : ${item.spo2 ?: "-"}"
                            )

                            Text(
                                text = "Kondisi : ${item.kondisi ?: "-"}"
                            )
                        }
                    }
                }
            }
        }
    }
}
