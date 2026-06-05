package com.example.pelatihankode.data.local

import android.content.ContentValues

class MorseDao(
    private val database: AppDatabase
) {

    fun getAllMorse(): List<MorseEntity> {
        val items = mutableListOf<MorseEntity>()
        val cursor = database.readableDatabase.query(
            AppDatabase.TABLE_MORSE,
            null,
            null,
            null,
            null,
            null,
            "${AppDatabase.COLUMN_HURUF} ASC"
        )

        cursor.use {
            while (it.moveToNext()) {
                items.add(it.toMorseEntity())
            }
        }

        return items
    }

    fun getMorseByHuruf(huruf: String): MorseEntity? {
        val cursor = database.readableDatabase.query(
            AppDatabase.TABLE_MORSE,
            null,
            "${AppDatabase.COLUMN_HURUF} = ?",
            arrayOf(huruf),
            null,
            null,
            null,
            "1"
        )

        return cursor.use {
            if (it.moveToFirst()) it.toMorseEntity() else null
        }
    }

    fun countMorse(): Int {
        val cursor = database.readableDatabase.rawQuery(
            "SELECT COUNT(*) FROM ${AppDatabase.TABLE_MORSE}",
            null
        )

        return cursor.use {
            if (it.moveToFirst()) it.getInt(0) else 0
        }
    }

    fun insertAll(items: List<MorseEntity>) {
        val writableDatabase = database.writableDatabase

        writableDatabase.beginTransaction()
        try {
            items.forEach { item ->
                writableDatabase.insertWithOnConflict(
                    AppDatabase.TABLE_MORSE,
                    null,
                    item.toContentValues(),
                    android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
                )
            }
            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }
    }

    private fun android.database.Cursor.toMorseEntity(): MorseEntity {
        return MorseEntity(
            huruf = getString(getColumnIndexOrThrow(AppDatabase.COLUMN_HURUF)),
            morse = getString(getColumnIndexOrThrow(AppDatabase.COLUMN_MORSE)),
            imageRes = getInt(getColumnIndexOrThrow(AppDatabase.COLUMN_IMAGE_RES)),
            soundRes = getInt(getColumnIndexOrThrow(AppDatabase.COLUMN_SOUND_RES))
        )
    }

    private fun MorseEntity.toContentValues(): ContentValues {
        return ContentValues().apply {
            put(AppDatabase.COLUMN_HURUF, huruf)
            put(AppDatabase.COLUMN_MORSE, morse)
            put(AppDatabase.COLUMN_IMAGE_RES, imageRes)
            put(AppDatabase.COLUMN_SOUND_RES, soundRes)
        }
    }
}

