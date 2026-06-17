package com.example.pelatihankode.data.local

import com.example.pelatihankode.data.local.HurufMorse

data class MorseEntity(
    val huruf: String,
    val morse: String,
    val imageRes: Int,
    val soundRes: Int
)

fun MorseEntity.toHurufMorse(): HurufMorse {
    return HurufMorse(
        huruf = huruf,
        morse = morse,
        imageRes = imageRes,
        soundRes = soundRes
    )
}

fun HurufMorse.toMorseEntity(): MorseEntity {
    return MorseEntity(
        huruf = huruf,
        morse = morse,
        imageRes = imageRes,
        soundRes = soundRes
    )
}

