package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.pelatihankode.data.local.AppDatabase
import com.example.pelatihankode.data.local.MorseEntity
import com.example.pelatihankode.data.local.RiwayatEntity
import com.example.pelatihankode.ui.components.JawabanCard
import com.example.pelatihankode.ui.components.ResultDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.pelatihankode.utils.MorsePlayer
import com.example.pelatihankode.utils.MorseVibrator


@Composable
fun QuizScreen(
    navController: NavController,
    siswaId: Long,
    bpm: Int?,
    spo2: Int?,
    kondisi: String?
){
    val context = LocalContext.current
    val riwayatDao = remember {
        AppDatabase
            .getDatabase(context)
            .riwayatDao()
    }

    var currentSoalHuruf by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var pilihanHuruf by rememberSaveable {
        mutableStateOf<List<String>>(emptyList())
    }
    var selectedAnswerHuruf by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var nomorSoal by rememberSaveable {
        mutableStateOf(1)
    }
    var jumlahBenar by rememberSaveable {
        mutableStateOf(0)
    }
    var jumlahSalah by rememberSaveable {
        mutableStateOf(0)
    }
    var skorFinal by rememberSaveable { mutableStateOf(0) }
    var benarFinal by rememberSaveable { mutableStateOf(0) }
    var salahFinal by rememberSaveable { mutableStateOf(0) }
    val tanggalSekarang = SimpleDateFormat(
        "dd-MM-yyyy HH:mm",
        Locale.getDefault()
    ).format(Date())
    var showResultDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var semuaMorse by remember {
        mutableStateOf<List<MorseEntity>>(emptyList())
    }
    val configuration = LocalConfiguration.current
    val soal = semuaMorse.firstOrNull {
        it.huruf == currentSoalHuruf
    }
    val pilihan = pilihanHuruf.mapNotNull { huruf ->
        semuaMorse.firstOrNull {
            it.huruf == huruf
        }
    }

    fun generateQuestion(
        data: List<MorseEntity>
    ) {
        if (data.isEmpty()) return

        val soalTerpilih = data.random()

        currentSoalHuruf = soalTerpilih.huruf

        val jawabanSalah = data
            .filter {
                it.huruf != soalTerpilih.huruf
            }
            .shuffled()
            .take(2)

        pilihanHuruf = (
                jawabanSalah +
                        soalTerpilih
                )
            .shuffled()
            .map {
                it.huruf
            }
    }
    LaunchedEffect(Unit) {
        android.util.Log.d(
            "QUIZ_MONITOR",
            "BPM=$bpm SPO2=$spo2 KONDISI=$kondisi"
        )

        semuaMorse = withContext(Dispatchers.IO) {
            android.util.Log.d(
                "QUIZ_ROTATE",
                "LOAD SELESAI size=${semuaMorse.size}"
            )
            AppDatabase
                .getDatabase(context)
                .morseDao()
                .getAllMorse()
        }

        if (
            currentSoalHuruf == null ||
            pilihanHuruf.isEmpty()
        ) {
            generateQuestion(semuaMorse)
        }
    }
    val isLandscape =
        configuration.orientation ==
                Configuration.ORIENTATION_LANDSCAPE
    if (
        semuaMorse.isEmpty()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Loading...")
        }

        return
    }
    android.util.Log.d(
        "QUIZ_ROTATE",
        "soal=$currentSoalHuruf pilihan=${pilihanHuruf.size}"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "QUIZ MORSE",
            fontSize = 32.sp,
            color = Color.Black
        )

        Text(
            text = "Soal $nomorSoal / 10",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LinearProgressIndicator(
            progress = {
                nomorSoal / 10f
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = soal?.morse ?: "...",
                    fontSize = 48.sp
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {

                        soal?.let {

                            MorsePlayer.play(
                                context,
                                it.soundRes
                            )
                            MorseVibrator.vibrateMorse(
                                context,
                                it.morse
                            )
                        }
                    }
                ) {

                    Text("🔊 Putar Morse")
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
//        soal?.let {
//
//            Image(
//                painter = painterResource(
//                    id = it.imageRes
//                ),
//                contentDescription = it.huruf,
//                modifier = Modifier.height(120.dp)
//            )
//        }
        Text(
            text = "Kode morse di atas merupakan huruf?",
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            pilihan.forEach {

                JawabanCard(

                    imageRes = it.imageRes,

                    selected =
                        selectedAnswerHuruf ==
                                it.huruf,

                    onClick = {

                        selectedAnswerHuruf = it.huruf
                    },

                    modifier = Modifier
                        .weight(1f)
                        .height(
                            if (isLandscape) 60.dp
                            else 90.dp
                        )
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            enabled = selectedAnswerHuruf != null,
            onClick = {
                val jawabanBenar =
                    selectedAnswerHuruf == soal?.huruf

                if (nomorSoal < 10) {
                    if (jawabanBenar) {
                        jumlahBenar++
                    } else {
                        jumlahSalah++
                    }

                    generateQuestion(semuaMorse)

                    selectedAnswerHuruf = null

                    nomorSoal++
                }else {

                    benarFinal =
                        if (jawabanBenar)
                            jumlahBenar + 1
                        else
                            jumlahBenar

                    salahFinal =
                        if (!jawabanBenar)
                            jumlahSalah + 1
                        else
                            jumlahSalah

                    skorFinal = benarFinal * 10

                    val riwayat = RiwayatEntity(

                        siswaId = siswaId,

                        tanggal = tanggalSekarang,

                        skor = skorFinal,

                        benar = benarFinal,

                        salah = salahFinal,

                        bpm = bpm,

                        spo2 = spo2,

                        kondisi = kondisi
                    )

                    riwayatDao.insertRiwayat(
                        riwayat
                    )
                    android.util.Log.d(
                        "RIWAYAT_TEST",
                        "INSERT BERHASIL: $riwayat"
                    )

                    showResultDialog = true
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (nomorSoal == 10)
                        "SELESAI"
                    else
                        "NEXT"
            )
        }
        if (showResultDialog) {

            ResultDialog(

                skor = skorFinal,

                jumlahBenar = benarFinal,

                jumlahSalah = salahFinal,

                onDismiss = {

                    showResultDialog = false

                    navController.popBackStack()
                }
            )
        }
        DisposableEffect(Unit) {

            onDispose {

                MorsePlayer.release()
            }
        }
    }
}

