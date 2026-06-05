package com.example.pelatihankode.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pelatihankode.data.local.SiswaEntity
import com.example.pelatihankode.ui.screen.Dynapuf
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.OutlinedButton

@Composable
fun SiswaCard(
    siswa: SiswaEntity,
    onPilih: () -> Unit,
    onEdit: () -> Unit,
    onDelete:() -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onPilih()
            },

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            Column(

                modifier = Modifier.fillMaxWidth(),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                siswa.fotoUri.takeIf {
                    it.isNotBlank() && it != "null"
                }?.let {

                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                } ?: run {

                    Card(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = siswa.nama.firstOrNull()?.toString() ?: "?",
                                fontSize = 28.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = siswa.nama,
                        fontFamily = Dynapuf,
                        fontSize = 22.sp,
                        color = Color.Black
                    )

                    Text(
                        text = siswa.kelas,
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                OutlinedButton(
                    onClick = onEdit
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
                Spacer(
                    modifier = Modifier.size(8.dp)
                )
                OutlinedButton(
                    onClick = onDelete
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}


