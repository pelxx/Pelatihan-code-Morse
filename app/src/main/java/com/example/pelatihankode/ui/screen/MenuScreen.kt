package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.pelatihankode.R
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.ui.components.MenuCard
import com.example.pelatihankode.ui.components.MonitoringDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


val DynapufMenu = FontFamily(
    Font(R.font.dynapuf)
)

@Composable
fun MenuScreen(
    navController: NavController,
    siswa: SiswaEntity?
) {
    var showMonitoringDialog by remember {
        mutableStateOf(false)
    }

    val menuItems = listOf(
        "BELAJAR" to "belajar",
        "QUIZ" to "quiz",
        "RIWAYAT" to "riwayat",
        "ABOUT" to "about"
    )

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

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MENU UTAMA",
            fontFamily = DynapufMenu,
            fontSize = 36.sp,
            color = Color.Black
        )

        siswa?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it.nama,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(menuItems) { (title, route) ->
                MenuCard(
                    text = title,
                    onClick = {
                        if (route == "quiz") {

                            showMonitoringDialog = true

                        } else {

                            navController.navigate(route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
        }
    }
    if (showMonitoringDialog) {

        MonitoringDialog(

            onDismiss = {

                showMonitoringDialog = false
            },

            onMulaiQuiz = {
                    bpm,
                    spo2,
                    kondisi ->

                showMonitoringDialog = false

                navController.navigate("quiz")
            }
        )
    }
}
