package com.example.pelatihankode.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.pelatihankode.R

object AlphabetPlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun play(
        context: Context,
        huruf: String
    ) {

        stop()

        val soundRes = when (huruf.uppercase()) {

            "A" -> R.raw.a
            "B" -> R.raw.b
            "C" -> R.raw.c
            "D" -> R.raw.d
            "E" -> R.raw.e
            "F" -> R.raw.f
            "G" -> R.raw.g
            "H" -> R.raw.h
            "I" -> R.raw.i
            "J" -> R.raw.j
            "K" -> R.raw.k
            "L" -> R.raw.l
            "M" -> R.raw.m
            "N" -> R.raw.n
            "O" -> R.raw.o
            "P" -> R.raw.p
            "Q" -> R.raw.q
            "R" -> R.raw.r
            "S" -> R.raw.s
            "T" -> R.raw.t
            "U" -> R.raw.u
            "V" -> R.raw.v
            "W" -> R.raw.w
            "X" -> R.raw.x
            "Y" -> R.raw.y
            "Z" -> R.raw.z

            else -> return
        }

        mediaPlayer = MediaPlayer.create(
            context,
            soundRes
        )

        mediaPlayer?.setOnCompletionListener {

            stop()
        }

        mediaPlayer?.start()
    }

    fun stop() {

        mediaPlayer?.release()

        mediaPlayer = null
    }
}