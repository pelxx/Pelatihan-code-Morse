package com.example.pelatihankode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pelatihankode.data.HurufMorse
import com.example.pelatihankode.R
import com.example.pelatihankode.ui.components.HurufCard


public val hurufList = listOf(

    HurufMorse("A", ".-", R.drawable.huruf_a, R.raw.morse_a),
    HurufMorse("B", "-...", R.drawable.huruf_b, R.raw.morse_b),
    HurufMorse("C", "-.-.", R.drawable.huruf_c, R.raw.morse_c),
    HurufMorse("D", "-..", R.drawable.huruf_d, R.raw.morse_d),
    HurufMorse("E", ".", R.drawable.huruf_e, R.raw.morse_e),
    HurufMorse("F", "..-.", R.drawable.huruf_f, R.raw.morse_f),
    HurufMorse("G", "--.", R.drawable.huruf_g, R.raw.morse_g),
    HurufMorse("H", "....", R.drawable.huruf_h, R.raw.morse_h),
    HurufMorse("I", "..", R.drawable.huruf_i, R.raw.morse_i),
    HurufMorse("J", ".---", R.drawable.huruf_j, R.raw.morse_j),
    HurufMorse("K", "-.-", R.drawable.huruf_k, R.raw.morse_k),
    HurufMorse("L", ".-..", R.drawable.huruf_l, R.raw.morse_l),
    HurufMorse("M", "--", R.drawable.huruf_m, R.raw.morse_m),
    HurufMorse("N", "-.", R.drawable.huruf_n, R.raw.morse_n),
    HurufMorse("O", "---", R.drawable.huruf_o, R.raw.morse_o),
    HurufMorse("P", ".--.", R.drawable.huruf_p, R.raw.morse_p),
    HurufMorse("Q", "--.-", R.drawable.huruf_q, R.raw.morse_q),
    HurufMorse("R", ".-.", R.drawable.huruf_r, R.raw.morse_r),
    HurufMorse("S", "...", R.drawable.huruf_s, R.raw.morse_s),
    HurufMorse("T", "-", R.drawable.huruf_t, R.raw.morse_t),
    HurufMorse("U", "..-", R.drawable.huruf_u, R.raw.morse_u),
    HurufMorse("V", "...-", R.drawable.huruf_v, R.raw.morse_v),
    HurufMorse("W", ".--", R.drawable.huruf_w, R.raw.morse_w),
    HurufMorse("X", "-..-", R.drawable.huruf_x, R.raw.morse_x),
    HurufMorse("Y", "-.--", R.drawable.huruf_y, R.raw.morse_y),
    HurufMorse("Z", "--..", R.drawable.huruf_z, R.raw.morse_z)

)
@Composable
fun BelajarScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "BELAJAR MORSE",
            fontSize = 32.sp,
            fontFamily = Dynapuf,

        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(

            columns = GridCells.Fixed(5),

            horizontalArrangement = Arrangement.spacedBy(12.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            items(hurufList) { item ->
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