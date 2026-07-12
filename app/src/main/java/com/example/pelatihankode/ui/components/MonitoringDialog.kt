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
    val bpmInt = bpm.toIntOrNull()

    val spo2Int = spo2.toIntOrNull()

    val isValid =
        bpmInt in 30..220 && spo2Int in 70..100

    AlertDialog(

        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        title = {
            Text(
                "Smart Check (Student Monitoring and Readiness Tracking)",
                fontFamily = Dynapuf
            )
        },

        text = {

            Column {

                OutlinedTextField(

                    value = bpm,

                    onValueChange = {
                        bpm = it.filter(Char::isDigit)
                            .take(3)
                    },

                    isError = bpm.isNotEmpty() &&
                            bpmInt !in 30..220,

                    label = {
                        Text(
                            "BPM",
                            fontFamily = DynapufSec
                        )
                    },

                    supportingText = {

                        if (
                            bpm.isNotEmpty() &&
                            bpmInt !in 30..200
                        ) {

                            Text("Rentang BPM: 30–200")
                        }
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(

                    value = spo2,

                    onValueChange = {
                        spo2 = it.filter(Char::isDigit)
                            .take(3)
                    },

                    isError = spo2.isNotEmpty() &&
                            spo2Int !in 70..100,

                    label = {
                        Text(
                            "SpO₂",
                            fontFamily = DynapufSec
                        )
                    },

                    supportingText = {

                        if (
                            spo2.isNotEmpty() &&
                            spo2Int !in 70..100
                        ) {

                            Text("Rentang SpO₂: 70–100%")
                        }
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

                Text(
                    text = "LANJUT",
                    fontFamily = DynapufSec
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "BATAL",
                    fontFamily = DynapufSec
                )
            }
        }
    )
}