package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pelatihankode.R

@Composable
fun AboutScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(
                R.drawable.logo_orang
            ),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Pelatihan KODE",
            fontSize = 30.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text =
                "Aplikasi pembelajaran kode Morse untuk membantu anak-anak autis mengenal huruf dan bunyi kode Morse.",
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Versi 1.0"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Dibuat oleh"
        )

        Text(
            text = "Fadillah Hayatus Zilal"
        )

        Text(
            text = "D3 Teknik Elektromedis"
        )
    }
}