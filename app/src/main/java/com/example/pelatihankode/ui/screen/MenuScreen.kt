package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pelatihankode.R
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.ui.components.MenuCard
import com.example.pelatihankode.ui.theme.AppBackgroundGradient


val DynapufMenu = FontFamily(
    Font(R.font.dynapufprimary)
)

@Composable
fun MenuScreen(
    navController: NavController,
    siswa: SiswaEntity?
) {

    val menuItems = listOf(
        Triple("BELAJAR MORSE", "belajar", R.drawable.morse),
        Triple("BELAJAR ALFABET", "alfabet", R.drawable.alfabet),
        Triple("RIWAYAT", "riwayat", R.drawable.history),
        Triple("ABOUT", "about", R.drawable.about)
    )

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
        Spacer(
            modifier = Modifier.height(9.dp)
        )
        Text(
            text = "MENU UTAMA",
            fontFamily = DynapufMenu,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        siswa?.let {

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = it.fotoUri,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = it.nama,
                fontSize = 20.sp,
                fontFamily = DynapufSec,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = it.kelas,
                fontSize = 16.sp,
                fontFamily = DynapufSec,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(menuItems) { (title, route, imageRes) ->

                MenuCard(
                    text = title,
                    imageRes = imageRes,
                    onClick = {

                        when(route) {
                            "riwayat" -> {
                                navController.navigate(
                                    "riwayat/${siswa?.id}"
                                )
                            }

                            else -> {
                                navController.navigate(route)
                            }
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Image(
            painter = painterResource(
                R.drawable.anakbelajar
            ),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )
    }
}
