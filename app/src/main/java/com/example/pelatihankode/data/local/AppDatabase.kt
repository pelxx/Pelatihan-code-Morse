package com.example.pelatihankode.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase private constructor(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val morseDao = MorseDao(this)
    private val siswaDao = SiswaDao(this)
    private val riwayatDao = RiwayatDao(this)

    fun riwayatDao(): RiwayatDao = riwayatDao

    override fun onCreate(db: SQLiteDatabase) {
        createTables(db)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        createTables(db)
        ensureSiswaFotoUriColumn(db)
        ensureSiswaUmurColumn(db)
    }

    fun morseDao(): MorseDao = morseDao

    fun siswaDao(): SiswaDao = siswaDao

    private fun createTables(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_MORSE (
                $COLUMN_HURUF TEXT PRIMARY KEY,
                $COLUMN_MORSE TEXT NOT NULL,
                $COLUMN_IMAGE_RES INTEGER NOT NULL,
                $COLUMN_SOUND_RES INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_SISWA (
                $COLUMN_SISWA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAMA TEXT NOT NULL,
                $COLUMN_UMUR INTEGER NOT NULL DEFAULT 0,
                $COLUMN_KELAS TEXT NOT NULL,
                $COLUMN_FOTO_URI TEXT NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_RIWAYAT (
                 $COLUMN_RIWAYAT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                 $COLUMN_RIWAYAT_SISWA_ID INTEGER NOT NULL,
                 $COLUMN_TANGGAL TEXT NOT NULL,
                 $COLUMN_BPM INTEGER,
                 $COLUMN_SPO2 INTEGER
    )
    """.trimIndent()
        )
    }

    private fun ensureSiswaFotoUriColumn(db: SQLiteDatabase) {
        val cursor = db.rawQuery("PRAGMA table_info($TABLE_SISWA)", null)
        val hasFotoUriColumn = cursor.use {
            var found = false
            while (it.moveToNext()) {
                val columnName = it.getString(it.getColumnIndexOrThrow("name"))
                if (columnName == COLUMN_FOTO_URI) {
                    found = true
                    break
                }
            }
            found
        }

        if (!hasFotoUriColumn) {
            db.execSQL(
                "ALTER TABLE $TABLE_SISWA ADD COLUMN $COLUMN_FOTO_URI TEXT NOT NULL DEFAULT ''"
            )
        }
    }

    private fun ensureSiswaUmurColumn(
        db: SQLiteDatabase
    ) {

        val cursor = db.rawQuery(
            "PRAGMA table_info($TABLE_SISWA)",
            null
        )

        val hasUmurColumn = cursor.use {

            var found = false

            while (it.moveToNext()) {

                val columnName =
                    it.getString(
                        it.getColumnIndexOrThrow("name")
                    )

                if (columnName == COLUMN_UMUR) {

                    found = true
                    break
                }
            }

            found
        }

        if (!hasUmurColumn) {

            db.execSQL(
                "ALTER TABLE $TABLE_SISWA ADD COLUMN $COLUMN_UMUR INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
    companion object {
        const val TABLE_MORSE = "morse"
        const val TABLE_SISWA = "siswa"
        const val COLUMN_HURUF = "huruf"
        const val COLUMN_MORSE = "morse"
        const val COLUMN_IMAGE_RES = "imageRes"
        const val COLUMN_SOUND_RES = "soundRes"
        const val COLUMN_SISWA_ID = "id"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_KELAS = "kelas"
        const val COLUMN_UMUR = "umur"
        const val COLUMN_FOTO_URI = "foto_uri"

        const val TABLE_RIWAYAT = "riwayat"

        const val COLUMN_RIWAYAT_ID = "id"
        const val COLUMN_RIWAYAT_SISWA_ID = "siswa_id"

        const val COLUMN_TANGGAL = "tanggal"

        const val COLUMN_BPM = "bpm"
        const val COLUMN_SPO2 = "spo2"
        private const val DATABASE_NAME = "morse_database.db"
        private const val DATABASE_VERSION = 7

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = AppDatabase(context.applicationContext)

                INSTANCE = instance
                instance
            }
        }
    }
}
