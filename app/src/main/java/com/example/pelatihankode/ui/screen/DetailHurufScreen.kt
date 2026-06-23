package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pelatihankode.R
import com.example.pelatihankode.data.local.HurufMorse
import com.example.pelatihankode.ui.theme.AppBackgroundGradient
import com.example.pelatihankode.utils.MorsePlayer
import com.example.pelatihankode.utils.MorseVibrator

val DynapufDetail = FontFamily(
    Font(R.font.dynapufprimary)
)

@Composable
fun DetailHurufScreen(

    item: HurufMorse

) {

    val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()

            .background(
                brush = Brush.verticalGradient(
                    colors = AppBackgroundGradient
                )
            )

            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Card(

            shape = RoundedCornerShape(24.dp)

        ) {

            Image(

                painter = painterResource(item.imageRes),

                contentDescription = null,

                modifier = Modifier.size(260.dp),

                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(

            text = item.huruf,

            fontSize = 64.sp,

            fontFamily = DynapufDetail,

            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(

            text = item.morse,

            fontSize = 42.sp,

            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                MorsePlayer.play(
                    context,
                    item.soundRes
                )

                MorseVibrator.vibrateMorse(
                    context,
                    item.morse
                )
            }
        ) {

            Text(
                text = "PLAY SOUND",
                fontSize = 20.sp
            )
        }
        DisposableEffect(Unit) {

            onDispose {

                MorsePlayer.release()
            }
        }
    }
}
