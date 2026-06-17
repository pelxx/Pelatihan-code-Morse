package com.example.pelatihankode.utils

import android.content.Context
import android.media.MediaPlayer

object MorsePlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun play(
        context: Context,
        soundRes: Int
    ) {

        // Kalau masih memutar, abaikan klik
        if (mediaPlayer?.isPlaying == true) {
            return
        }

        mediaPlayer = MediaPlayer.create(
            context,
            soundRes
        )

        mediaPlayer?.apply {

            setOnCompletionListener {

                release()

                mediaPlayer = null
            }

            start()
        }
    }

    fun release() {

        mediaPlayer?.release()

        mediaPlayer = null
    }
}