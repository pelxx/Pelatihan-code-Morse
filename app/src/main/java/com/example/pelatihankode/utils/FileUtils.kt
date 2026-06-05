package com.example.pelatihankode.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun createStudentPhotoUri(
    context: Context
): Uri {

    val imageFile = File(

        context.filesDir,

        "student_photo_${System.currentTimeMillis()}.jpg"
    )

    return FileProvider.getUriForFile(

        context,

        "${context.packageName}.provider",

        imageFile
    )
}
