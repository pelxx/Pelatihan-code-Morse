package com.example.pelatihankode.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.utils.createStudentPhotoUri

@Composable
fun SiswaDialog(
    title: String,
    siswa: SiswaEntity? = null,
    onDismiss: () -> Unit,
    onSave: (String, Int, String, String) -> Unit
) {

    var nama by remember {
        mutableStateOf(
            siswa?.nama.orEmpty()
        )
    }
    var umur by remember {

        mutableStateOf(

            siswa?.umur?.toString() ?: ""
        )
    }
    var kelas by remember {
        mutableStateOf(
            siswa?.kelas.orEmpty()
        )
    }

    var fotoUri by remember {

        mutableStateOf<Uri?>(
            siswa?.fotoUri?.takeIf {
                it.isNotBlank()
            }?.let {
                it.toUri()
            }
        )
    }

    val context = LocalContext.current
    val cameraUri = remember {
        createStudentPhotoUri(context)
    }

    val isValid =
        nama.isNotBlank() && kelas.isNotBlank() && umur.isNotBlank()

    val galleryLauncher =
        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.GetContent()

        ) { uri ->

            if (uri != null) {

                fotoUri = uri
            }
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.TakePicture()

        ) { success ->

            if (success) {

                fotoUri = cameraUri
            }
        }
    val permissionLauncher =
        rememberLauncherForActivityResult(

            contract =
                ActivityResultContracts.RequestPermission()

        ) { granted ->

            if (granted) {

                cameraLauncher.launch(
                    cameraUri
                )

            } else {

                Toast.makeText(
                    context,
                    "Permission kamera ditolak",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    AlertDialog(

        onDismissRequest = {

        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        title = {

            Text(
                text = title
            )
        },

        text = {

            Column(
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                ),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                OutlinedTextField(
                    value = nama,

                    onValueChange = {
                        nama = it
                    },

                    label = {
                        Text("Nama")
                    },

                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(

                    value = umur,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {

                        umur = it.filter { c ->
                            c.isDigit()
                        }
                    },

                    label = {
                        Text("Umur")
                    },

                    singleLine = true,

                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = kelas,

                    onValueChange = {
                        kelas = it
                    },

                    label = {
                        Text("Kelas")
                    },

                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(

                    onClick = {

                        if (

                            ContextCompat.checkSelfPermission(

                                context,
                                Manifest.permission.CAMERA

                            ) == PackageManager.PERMISSION_GRANTED

                        ) {

                            cameraLauncher.launch(
                                cameraUri
                            )

                        } else {

                            permissionLauncher.launch(
                                Manifest.permission.CAMERA
                            )
                        }
                    }

                ) {

                    Text(
                        text = "BUKA CAMERA"
                    )
                }


                Button(

                    onClick = {

                        galleryLauncher.launch(
                            "image/*"
                        )
                    }

                ) {

                    Text(
                        text = "BUKA GALERI"
                    )
                }

                fotoUri?.let {

                    AsyncImage(

                        model = it,

                        contentDescription = null,

                        modifier = Modifier
                            .size(120.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            ),

                        contentScale = ContentScale.Crop
                    )
                }
            }
        },

        confirmButton = {

            Button(

                onClick = {

                    onSave(
                        nama.trim(),
                        umur.toIntOrNull() ?: 0,
                        kelas.trim(),
                        fotoUri?.toString().orEmpty()
                    )
                },

                enabled = isValid
            ) {

                Text(
                    text = "SIMPAN"
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "BATAL"
                )
            }
        }
    )
}