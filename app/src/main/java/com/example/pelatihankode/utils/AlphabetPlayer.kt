package com.example.pelatihankode.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.pelatihankode.data.alphabetAudioMap

object AlphabetPlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun play(
        context: Context,
        huruf: String
    ) {

        stop()

        val soundRes = alphabetAudioMap[huruf.uppercase()]
            ?: return

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