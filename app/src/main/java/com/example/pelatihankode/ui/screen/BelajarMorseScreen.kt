package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pelatihankode.data.local.HurufMorse
import com.example.pelatihankode.data.defaultHurufList
import com.example.pelatihankode.ui.components.HurufCard
import com.example.pelatihankode.ui.theme.AppBackgroundGradient

@Composable
fun BelajarScreen(
    navController: NavController,
    hurufItems: List<HurufMorse> = defaultHurufList
) {

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

        Text(
            text = "BELAJAR MORSE",
            fontSize = 32.sp,
            fontFamily = Dynapuf,
            color = MaterialTheme.colorScheme.onBackground

        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

            horizontalArrangement = Arrangement.spacedBy(12.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            items(hurufItems) { item ->
                HurufCard(
                    item = item,

                    onClick = {

                        navController.navigate("detail/${item.huruf}")

                    }
                )

            }
        }
    }
}
