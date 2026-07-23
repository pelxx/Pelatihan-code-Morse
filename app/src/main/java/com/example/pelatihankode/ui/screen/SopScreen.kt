package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SopScreen(
    navController: NavController
) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "MORSE GO",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Standard Operating Procedure",
                            fontSize = 12.sp
                        )
                    }
                }
            )
        },

        bottomBar = {

            Surface(shadowElevation = 8.dp) {

                Button(

                    onClick = {

                        navController.navigate("siswa") {
                            popUpTo("sop") {
                                inclusive = true
                            }
                        }

                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {

                    Text("MULAI")

                }
            }

        }

    ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            item {

                Text(
                    text = "Sebelum menggunakan aplikasi, pastikan prosedur berikut telah dilakukan.",
                    fontSize = 16.sp
                )

            }

            item {

                SopCard(

                    icon = Icons.Default.PersonAdd,

                    title = "Registrasi Siswa",

                    content =
                        "Masukkan nama, umur, kelas, dan foto siswa. Pastikan seluruh data sudah benar sebelum disimpan."

                )

            }

            item {

                SopCard(

                    icon = Icons.Default.MonitorHeart,

                    title = "Pemeriksaan Kondisi Fisiologis",

                    content =
                        "Lakukan pengukuran Heart Rate (BPM) dan Saturasi Oksigen (SpO₂), kemudian masukkan hasil sesuai alat ukur."

                )

            }

            item {

                SopCard(

                    Icons.AutoMirrored.Filled.List,

                    title = "Evaluasi Hasil Monitoring",

                    content =
                        "Jika BPM dan SpO₂ berada dalam rentang normal, siswa dapat melanjutkan ke menu pembelajaran. Jika berada di luar rentang normal, guru atau pendamping disarankan melakukan evaluasi kondisi siswa terlebih dahulu."

                )

            }

            item {

                SopCard(

                    icon = Icons.Default.School,

                    title = "Menu Pembelajaran",

                    content =
                        "Aplikasi menyediakan tiga fitur utama yaitu Belajar Kode Morse, Belajar Alfabet, dan Riwayat Belajar Siswa."

                )

            }

            item {

                SopCard(

                    icon = Icons.Default.Info,

                    title = "Catatan",

                    content =
                        "MORSE GO merupakan media pembelajaran kode Morse yang dilengkapi dengan monitoring kondisi fisiologis sebagai skrining awal. Hasil pengukuran tidak digunakan sebagai dasar diagnosis medis dan tidak menggantikan pemeriksaan oleh tenaga kesehatan."

                )

            }

        }

    }

}

@Composable
fun SopCard(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String,

    content: String

) {

    Card(

        shape = RoundedCornerShape(16.dp),

        modifier = Modifier.fillMaxWidth()

    ) {

        Row(

            modifier = Modifier.padding(16.dp)

        ) {

            Icon(

                imageVector = icon,

                contentDescription = null

            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(

                    text = title,

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp

                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(

                    text = content,

                    fontSize = 14.sp

                )

            }

        }

    }

}