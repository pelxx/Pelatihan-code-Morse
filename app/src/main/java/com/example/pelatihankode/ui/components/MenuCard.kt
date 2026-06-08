package com.example.pelatihankode.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pelatihankode.R

val DynapufMenu = FontFamily(
    Font(R.font.dynapuf)
)

@Composable
fun MenuCard(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = text,
                fontFamily = DynapufMenu,
                fontSize = 22.sp,
                color = Color.Black
            )
        }
    }
}
