package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.pelatihankode.R
import com.example.pelatihankode.data.defaultHurufList
import com.example.pelatihankode.ui.components.HurufCard
import com.example.pelatihankode.ui.theme.AppBackgroundGradient

val DynapufAlphabet = FontFamily(
    Font(R.font.dynapufprimary)
)

@Composable
fun AlfabetScreen(

    navController: NavController

) {

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

        Text(

            text = "BELAJAR ALFABET",
            fontFamily = DynapufAlphabet,
            textAlign = TextAlign.Center,
            fontSize = 34.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

            verticalArrangement = Arrangement.spacedBy(16.dp),

            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            items(defaultHurufList) { item ->

                HurufCard(

                    item = item,

                    onClick = {

                        navController.navigate(
                            "detailAlfabet/${item.huruf}"
                        )
                    }
                )
            }
        }
    }
}
