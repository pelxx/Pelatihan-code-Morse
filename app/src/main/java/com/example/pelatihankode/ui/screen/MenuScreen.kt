package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.example.pelatihankode.R
import com.example.pelatihankode.ui.components.MenuCard

val DynapufMenu = FontFamily(
    Font(R.font.dynapuf)
)

@Composable
fun MenuScreen(navController: NavController) {

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

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            MenuCard(
                "BELAJAR",
                onClick = {
                    navController.navigate("belajar")
                })

            MenuCard("QUIZ",
                onClick = {
                    navController.navigate("quiz")
                })
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            MenuCard("RIWAYAT",
                onClick = {
                    navController.navigate("riwayat")
                })

            MenuCard("ABOUT",
                onClick = {
                    navController.navigate("about")
                })
        }
    }
}