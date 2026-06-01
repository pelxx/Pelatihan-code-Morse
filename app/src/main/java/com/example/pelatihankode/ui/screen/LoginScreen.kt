package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pelatihankode.R

val Dynapuf = FontFamily(
    Font(R.font.dynapuf)
)

@Composable
fun LoginScreen(navController: NavController) {

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
            text = "PEMBELAJARAN KODE MORSE",
            fontSize = 40.sp,
            fontFamily = Dynapuf
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(20.dp),
            label = {
                Text("Email")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(20.dp),
            label = {
                Text("Password")
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {navController.navigate("menu") },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),

            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(60.dp)

        ) {

            Text(
                text = "LOGIN",
                fontFamily = Dynapuf,
                fontSize = 24.sp,
                color = Color.Black
            )
        }
    }

}