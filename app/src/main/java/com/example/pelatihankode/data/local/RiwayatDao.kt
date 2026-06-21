package com.example.pelatihankode.data.local

import android.content.ContentValues

class RiwayatDao(
    private val database: AppDatabase
) {

    fun getRiwayatBySiswa(
        siswaId: Long
    ): List<RiwayatEntity> {

        val items = mutableListOf<RiwayatEntity>()

        val cursor = database.readableDatabase.query(

            AppDatabase.TABLE_RIWAYAT,

            null,

            "${AppDatabase.COLUMN_RIWAYAT_SISWA_ID} = ?",

            arrayOf(siswaId.toString()),

            null,

            null,

            "${AppDatabase.COLUMN_TANGGAL} DESC"
        )

        cursor.use {

            while (it.moveToNext()) {

                items.add(
                    it.toRiwayatEntity()
                )
            }
        }

        return items
    }

    fun insertRiwayat(
        riwayat: RiwayatEntity
    ): Long {

        return database.writableDatabase.insert(

            AppDatabase.TABLE_RIWAYAT,

            null,

            createContentValues(
                riwayat
            )
        )
    }

    fun deleteRiwayat(
        id: Long
    ) {

        database.writableDatabase.delete(

            AppDatabase.TABLE_RIWAYAT,

            "${AppDatabase.COLUMN_RIWAYAT_ID} = ?",

            arrayOf(id.toString())
        )
    }

    private fun android.database.Cursor.toRiwayatEntity(): RiwayatEntity {

        return RiwayatEntity(

            id = getLong(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_RIWAYAT_ID
                )
            ),

            siswaId = getLong(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_RIWAYAT_SISWA_ID
                )
            ),

            tanggal = getString(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_TANGGAL
                )
            ),

            bpm = if (
                isNull(
                    getColumnIndexOrThrow(
                        AppDatabase.COLUMN_BPM
                    )
                )
            ) null
            else getInt(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_BPM
                )
            ),

            spo2 = if (
                isNull(
                    getColumnIndexOrThrow(
                        AppDatabase.COLUMN_SPO2
                    )
                )
            ) null
            else getInt(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_SPO2
                )
            ),

            kondisi = getString(
                getColumnIndexOrThrow(
                    AppDatabase.COLUMN_KONDISI
                )
            )
        )
    }

    private fun createContentValues(
        riwayat: RiwayatEntity
    ): ContentValues {

        return ContentValues().apply {

            put(
                AppDatabase.COLUMN_RIWAYAT_SISWA_ID,
                riwayat.siswaId
            )

            put(
                AppDatabase.COLUMN_TANGGAL,
                riwayat.tanggal
            )

            put(
                AppDatabase.COLUMN_BPM,
                riwayat.bpm
            )

            put(
                AppDatabase.COLUMN_SPO2,
                riwayat.spo2
            )

            put(
                AppDatabase.COLUMN_KONDISI,
                riwayat.kondisi
            )
        }
    }
}