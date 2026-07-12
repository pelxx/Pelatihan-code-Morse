package com.example.pelatihankode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.*
import com.example.pelatihankode.data.defaultHurufList
import com.example.pelatihankode.data.local.AppDatabase
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.data.local.toHurufMorse
import com.example.pelatihankode.data.local.toMorseEntity
import com.example.pelatihankode.ui.screen.AboutScreen
import com.example.pelatihankode.ui.screen.AlfabetScreen
import com.example.pelatihankode.ui.screen.BelajarScreen
import com.example.pelatihankode.ui.screen.DetailAlfabetScreen
import com.example.pelatihankode.ui.screen.MenuScreen
import com.example.pelatihankode.ui.screen.DetailHurufScreen
import com.example.pelatihankode.ui.screen.RiwayatScreen
import com.example.pelatihankode.ui.screen.SiswaScreen
import com.example.pelatihankode.ui.theme.PelatihanKODETheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val morseDao = database.morseDao()
        val siswaDao = database.siswaDao()


        setContent {

            PelatihanKODETheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                var hurufItems by remember { mutableStateOf(defaultHurufList) }
                val siswaItems = remember { mutableStateListOf<SiswaEntity>() }

                suspend fun refreshSiswa() {

                    val data = withContext(Dispatchers.IO) {
                        siswaDao.getAllSiswa()
                    }

                    siswaItems.clear()
                    siswaItems.addAll(data)
                }

                LaunchedEffect(Unit) {
                    hurufItems = withContext(Dispatchers.IO) {
                        morseDao.insertAll(defaultHurufList.map { it.toMorseEntity() })

                        morseDao.getAllMorse()
                            .map { it.toHurufMorse() }
                            .ifEmpty { defaultHurufList }
                    }
                    refreshSiswa()
                    println("siswa refresh")
                }

                NavHost(
                    navController = navController,
                    startDestination = "siswa"
                ) {

                    composable("siswa") {
                        SiswaScreen(
                            navController = navController,
                            siswaItems = siswaItems,
                            onTambahSiswa = { nama, umur, kelas, fotoUri ->

                                coroutineScope.launch {

                                    withContext(Dispatchers.IO) {

                                        siswaDao.insertSiswa(
                                            nama,
                                            umur,
                                            kelas,
                                            fotoUri
                                        )
                                    }
                                    refreshSiswa()
                                }
                            },
                            onEditSiswa = { siswa ->

                                coroutineScope.launch {

                                    withContext(Dispatchers.IO) {

                                        siswaDao.updateSiswa(
                                            siswa
                                        )
                                    }

                                    refreshSiswa()
                                }
                            } ,

                            onDeleteSiswa = { id ->

                                coroutineScope.launch {

                                    withContext(Dispatchers.IO) {

                                        siswaDao.deleteSiswa(id)
                                    }

                                    refreshSiswa()
                                }
                            }
                        )
                    }
                    composable("alfabet") {

                        AlfabetScreen(navController)

                    }

                    composable(

                        route = "detailAlfabet/{huruf}"

                    ) { backStackEntry ->

                        val huruf = backStackEntry.arguments?.getString("huruf")

                        val item = defaultHurufList.first {

                            it.huruf == huruf

                        }

                        DetailAlfabetScreen(item)
                    }
                    composable("menu/{siswaId}") { backStackEntry ->
                        val siswaId = backStackEntry.arguments
                            ?.getString("siswaId")
                            ?.toLongOrNull()
                        val selectedSiswa = siswaItems.find { it.id == siswaId }

                        MenuScreen(

                            navController = navController,

                            siswa = selectedSiswa,

                        )
                    }

                    composable("belajar") {
                        BelajarScreen(
                            navController = navController,
                            hurufItems = hurufItems
                        )
                    }

                    composable(
                        "quiz/{siswaId}"
                    ) { backStackEntry ->

                        val siswaId =
                            backStackEntry.arguments
                                ?.getString("siswaId")
                                ?.toLongOrNull() ?: 0L
                    }

                    composable(
                        "riwayat/{siswaId}"
                    ) { backStackEntry ->

                        val siswaId =
                            backStackEntry.arguments
                                ?.getString("siswaId")
                                ?.toLongOrNull() ?: 0L

                        val selectedSiswa =
                            siswaItems.find {
                                it.id == siswaId
                            }
                        RiwayatScreen(
                            siswaId = siswaId,
                            siswa = selectedSiswa
                        )
                    }

                    composable("about") {
                        AboutScreen()
                    }

                    composable("detail/{huruf}") { backStackEntry ->

                        val huruf = backStackEntry.arguments
                            ?.getString("huruf") ?: ""

                        val selectedHuruf = hurufItems.find {

                            it.huruf == huruf

                        }

                        selectedHuruf?.let {

                            DetailHurufScreen(it)

                        }
                    }
                }

            }
        }
    }

}
