package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.pelatihankode.utils.AlphabetPlayer

val DynapufAlphabetDetail = FontFamily(
    Font(R.font.dynapufprimary)
)

@Composable
fun DetailAlfabetScreen(

    item: HurufMorse

) {

    val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

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

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(

            text = item.huruf,

            fontFamily = DynapufAlphabetDetail,

            fontSize = 64.sp,

            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        Button(

            onClick = {

                AlphabetPlayer.play(
                    context,
                    item.huruf
                )
            }

        ) {

            Text(
                text = "PLAY SUARA",
                fontFamily = DynapufSec
            )
        }
    }
}
