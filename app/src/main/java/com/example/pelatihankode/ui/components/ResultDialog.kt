package com.example.pelatihankode.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun ResultDialog(
    skor: Int,
    jumlahBenar: Int,
    jumlahSalah: Int,
    onDismiss: () -> Unit
) {

    AlertDialog(

        onDismissRequest = {

        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        title = {
            Text("HASIL QUIZ")
        },

        text = {

            Text(
                """
                Skor : $skor
                
                Benar : $jumlahBenar
                Salah : $jumlahSalah
                """.trimIndent()
            )
        },

        confirmButton = {

            Button(
                onClick = onDismiss
            ) {

                Text("OK")
            }
        }
    )
}