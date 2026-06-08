package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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

    var riwayatList by remember {
        mutableStateOf<List<RiwayatEntity>>(emptyList())
    }

    LaunchedEffect(Unit) {

        riwayatList = withContext(
            Dispatchers.IO
        ) {

            AppDatabase
                .getDatabase(context)
                .riwayatDao()
                .getRiwayatBySiswa(
                    siswaId
                )
        }
    }

    if (riwayatList.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize()
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

            items(riwayatList) { item ->

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                ) {
                    siswa?.let {

                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

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
                                    Text(
                                        text = item.tanggal,
                                        fontSize = 16.sp
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text(
                                        text = "Skor : ${item.skor}"
                                    )

                                    Text(
                                        text = "Benar : ${item.benar}"
                                    )

                                    Text(
                                        text = "Salah : ${item.salah}"
                                    )

                                    Text(
                                        text = "BPM : ${item.bpm}"
                                    )

                                    Text(
                                        text = "SpO₂ : ${item.spo2}"
                                    )

                                    Text(
                                        text = "Kondisi : ${item.kondisi}"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}