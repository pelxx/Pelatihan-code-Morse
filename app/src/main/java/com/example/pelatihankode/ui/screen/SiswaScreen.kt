package com.example.pelatihankode.ui.screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.pelatihankode.R
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.ui.components.BpmWarningDialog
import com.example.pelatihankode.ui.components.MonitoringDialog
import com.example.pelatihankode.ui.components.SiswaCard
import com.example.pelatihankode.ui.components.SiswaDialog


val Dynapuf = FontFamily(
    Font(R.font.dynapufprimary)
)
val DynapufSec = FontFamily(
    Font(R.font.dynapufsecondary)
)

@Composable
fun SiswaScreen(
    navController: NavController,
    siswaItems: List<SiswaEntity>,
    onTambahSiswa: (String, Int, String, String) -> Unit,
    onEditSiswa: (SiswaEntity) -> Unit,
    onDeleteSiswa: (Long) -> Unit
) {

    var editedSiswa by remember {
        mutableStateOf<SiswaEntity?>(null)
    }

    val context = LocalContext.current
    var siswaHapus by remember {
        mutableStateOf<SiswaEntity?>(null)
    }
    var showTambahDialog by remember {
        mutableStateOf(false)
    }
    var selectedSiswa by remember {
        mutableStateOf<SiswaEntity?>(null)
    }

    var showMonitoringDialog by remember {
        mutableStateOf(false)
    }

    var showBpmWarning by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFFF4FC3)
                    )
                )
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(48.dp)
        )

        Text(
            text = "PILIH SISWA",

            fontSize = 40.sp,

            fontFamily = Dynapuf
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                showTambahDialog = true
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {

            Text(
                text = "TAMBAH SISWA",

                fontFamily = Dynapuf,

                fontSize = 18.sp
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

            verticalArrangement = Arrangement.spacedBy(12.dp),

            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (siswaItems.isEmpty()) {

                item {

                    Text(
                        text = "Belum ada siswa",
                        fontFamily = DynapufSec,
                        fontSize = 18.sp,

                        color = Color.Black
                    )
                }
            }

            items(

                items = siswaItems,

                key = { it.id }

            ) { siswa ->

                SiswaCard(

                    siswa = siswa,

                    onPilih = {

                        selectedSiswa = siswa

                        showMonitoringDialog = true
                    },

                    onEdit = {

                        editedSiswa = siswa
                    },

                    onDelete = {

                        siswaHapus=siswa
                    }
                )
            }
        }
    }
    if (showBpmWarning) {

        BpmWarningDialog(

            onDismiss = {

                showBpmWarning = false

                selectedSiswa = null
            }
        )
    }

    if (showTambahDialog) {

        SiswaDialog(

            title = "Tambah Siswa",

            onDismiss = {

                showTambahDialog = false
            },

            onSave = {
                    nama,
                    umur,
                    kelas,
                    fotoUri ->

                onTambahSiswa(
                    nama,
                    umur,
                    kelas,
                    fotoUri
                )

                Toast.makeText(
                    context,
                    "Siswa ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()

                showTambahDialog = false
            }
        )
    }

    editedSiswa?.let { siswa ->

        SiswaDialog(

            title = "Edit Siswa",

            siswa = siswa,

            onDismiss = {

                editedSiswa = null
            },

            onSave = {
                    nama,
                    umur,
                    kelas,
                    fotoUri ->

                onEditSiswa(
                    siswa.copy(
                        nama = nama,
                        umur = umur,
                        kelas = kelas,
                        fotoUri = fotoUri
                    )
                )

                editedSiswa = null
            }
        )
    }
    siswaHapus?.let { siswa ->

        AlertDialog(

            onDismissRequest = {
                // jangan tutup dialog
            },

            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),

            title = {
                Text("Hapus Siswa")
            },

            text = {

                Text(
                    "Apakah Anda yakin ingin menghapus siswa ${siswa.nama}?"
                )
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        onDeleteSiswa(
                            siswa.id
                        )

                        Toast.makeText(
                            context,
                            "Siswa dihapus",
                            Toast.LENGTH_SHORT
                        ).show()

                        siswaHapus = null
                    }

                ) {

                    Text("HAPUS")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        siswaHapus = null
                    }

                ) {

                    Text("BATAL")
                }
            }
        )
    }
    if (

        showMonitoringDialog &&
        selectedSiswa != null

    ) {

        MonitoringDialog(

            onDismiss = {

                showMonitoringDialog = false

                selectedSiswa = null
            },

            onMonitoringSelesai = { bpm, spo2, kondisi ->

                showMonitoringDialog = false

                if ((bpm ?: 0) > 100) {

                    showBpmWarning = true

                } else {

                    navController.navigate(
                        "menu/${selectedSiswa!!.id}"
                    )

                    selectedSiswa = null
                }
            }
        )
    }
}
