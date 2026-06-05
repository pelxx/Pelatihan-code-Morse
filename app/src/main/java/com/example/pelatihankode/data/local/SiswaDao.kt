package com.example.pelatihankode.data.local

import android.content.ContentValues

class SiswaDao(
    private val database: AppDatabase
) {

    fun getAllSiswa(): List<SiswaEntity> {
        val items = mutableListOf<SiswaEntity>()
        val cursor = database.readableDatabase.query(
            AppDatabase.TABLE_SISWA,
            null,
            null,
            null,
            null,
            null,
            "${AppDatabase.COLUMN_NAMA} ASC"
        )

        cursor.use {
            while (it.moveToNext()) {
                items.add(it.toSiswaEntity())
            }
        }

        return items
    }

    fun deleteSiswa(id: Long) {

        database.writableDatabase.delete(

            AppDatabase.TABLE_SISWA,

            "${AppDatabase.COLUMN_SISWA_ID} = ?",

            arrayOf(id.toString())
        )
    }

    fun getSiswaById(id: Long): SiswaEntity? {
        val cursor = database.readableDatabase.query(
            AppDatabase.TABLE_SISWA,
            null,
            "${AppDatabase.COLUMN_SISWA_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            "1"
        )

        return cursor.use {
            if (it.moveToFirst()) it.toSiswaEntity() else null
        }
    }

    fun insertSiswa(
        nama: String,
        umur: Int,
        kelas: String,
        fotoUri: String
    ): Long {
        return database.writableDatabase.insert(
            AppDatabase.TABLE_SISWA,
            null,
            createContentValues(nama, umur, kelas, fotoUri)
        )
    }

    fun updateSiswa(siswa: SiswaEntity) {
        database.writableDatabase.update(
            AppDatabase.TABLE_SISWA,
            createContentValues(siswa.nama, siswa.umur ,siswa.kelas, siswa.fotoUri),
            "${AppDatabase.COLUMN_SISWA_ID} = ?",
            arrayOf(siswa.id.toString())
        )
    }

    private fun android.database.Cursor.toSiswaEntity(): SiswaEntity {
        return SiswaEntity(
            id = getLong(getColumnIndexOrThrow(AppDatabase.COLUMN_SISWA_ID)),
            nama = getString(getColumnIndexOrThrow(AppDatabase.COLUMN_NAMA)),
            umur = getInt(getColumnIndexOrThrow(AppDatabase.COLUMN_UMUR)),
            kelas = getString(getColumnIndexOrThrow(AppDatabase.COLUMN_KELAS)),
            fotoUri = getString(getColumnIndexOrThrow(AppDatabase.COLUMN_FOTO_URI))
        )
    }

    private fun createContentValues(
        nama: String,
        umur: Int,
        kelas: String,
        fotoUri: String): ContentValues {
        return ContentValues().apply {
            put(AppDatabase.COLUMN_NAMA, nama)
            put(AppDatabase.COLUMN_UMUR, umur)
            put(AppDatabase.COLUMN_KELAS, kelas)
            put(AppDatabase.COLUMN_FOTO_URI, fotoUri)
        }
    }
}
