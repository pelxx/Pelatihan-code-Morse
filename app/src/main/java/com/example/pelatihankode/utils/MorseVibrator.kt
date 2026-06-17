package com.example.pelatihankode.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object MorseVibrator {

    private const val UNIT = 240L      // 5 WPM
    private const val DOT = UNIT
    private const val DASH = UNIT * 3
    private const val GAP = UNIT

    fun vibrateMorse(
        context: Context,
        morse: String
    ) {

        val pattern = mutableListOf<Long>()

        // delay awal
        pattern.add(0)

        morse.forEachIndexed { index, symbol ->

            when (symbol) {

                '.' -> pattern.add(DOT)

                '-' -> pattern.add(DASH)
            }

            // jeda antar simbol
            if (index != morse.lastIndex) {

                pattern.add(GAP)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            val vibrator = context
                .getSystemService(VibratorManager::class.java)
                .defaultVibrator

            vibrator.vibrate(

                VibrationEffect.createWaveform(

                    pattern.toLongArray(),

                    -1
                )
            )

        } else {

            @Suppress("DEPRECATION")
            val vibrator = context.getSystemService(
                Context.VIBRATOR_SERVICE
            ) as Vibrator

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                vibrator.vibrate(

                    VibrationEffect.createWaveform(

                        pattern.toLongArray(),

                        -1
                    )
                )

            } else {

                @Suppress("DEPRECATION")
                vibrator.vibrate(
                    pattern.toLongArray(),
                    -1
                )
            }
        }
    }
}