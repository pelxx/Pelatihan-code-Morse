package com.example.pelatihankode.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.DialogProperties

@Composable
fun MonitoringDialog(

    onDismiss: () -> Unit,

    onMonitoringSelesai: (
        Int?,
        Int?
    ) -> Unit
) {

    var bpm by remember {
        mutableStateOf("")
    }

    var spo2 by remember {
        mutableStateOf("")
    }
    val isValid =
        bpm.isNotBlank() && spo2.isNotBlank()

    AlertDialog(

        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        title = {
            Text("Smart Check (Student Monitoring and Readiness Tracking)")
        },

        text = {

            Column {

                OutlinedTextField(

                    value = bpm,

                    onValueChange = {
                        bpm = it.filter { c ->
                            c.isDigit()
                        }
                    },

                    label = {
                        Text("BPM")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(

                    value = spo2,

                    onValueChange = {
                        spo2 = it.filter { c ->
                            c.isDigit()
                        }
                    },

                    label = {
                        Text("SpO₂")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {

            TextButton(
            enabled = isValid,
                onClick = {

                    onMonitoringSelesai(

                        bpm.toIntOrNull(),

                        spo2.toIntOrNull(),

                    )
                }
            ) {

                Text("LANJUT")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("BATAL")
            }
        }
    )
}