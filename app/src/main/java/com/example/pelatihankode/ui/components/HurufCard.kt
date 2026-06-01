package com.example.pelatihankode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pelatihankode.data.HurufMorse

@Composable
fun HurufCard(
    item: HurufMorse,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier

            .aspectRatio(1f)

            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(item.imageRes),

                contentDescription = null,

                modifier = Modifier.fillMaxSize(),

                contentScale = ContentScale.Crop
            )
        }
    }
}