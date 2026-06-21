package com.example.pelatihankode.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun BpmWarningDialog(

    onDismiss: () -> Unit

) {

    AlertDialog(

        onDismissRequest = {},

        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        title = {

            Text(
                text = "Peringatan"
            )
        },

        text = {

            Text(
                text =
                    "Detak jantung melebihi batas normal (>100 BPM).\n\n" +
                            "Silakan beristirahat terlebih dahulu sebelum menggunakan aplikasi."
            )
        },

        confirmButton = {

            TextButton(

                onClick = onDismiss

            ) {

                Text(
                    text = "OK"
                )
            }
        }
    )
}