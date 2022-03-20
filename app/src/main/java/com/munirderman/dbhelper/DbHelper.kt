package com.munirderman.dbhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.widget.Toast
import java.io.File

class DbHelper (val context: Context) : SQLiteOpenHelper(context,DbHelper.DATABASE_NAME,null,DbHelper.DATABASE_VERSION) {
    private val TABLE_NAME="ButunSohbetler"
    private val COL_ID = "Id"
    private val COL_TITLE = "Title"
    private val COL_Description = "Description"
    private val COL_VideoLink = "VideoLink"
    companion object {
        public val DATABASE_NAME = "Munirderman.db"//database adı
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
//        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_TITLE  VARCHAR(256),$COL_Description  VARCHAR(256),$COL_VideoLink  INTEGER)"
//        db?.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

//    fun insertData(sohbetler:ButunSohbetler){
//        val sqliteDB = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(COL_NAME , sohbetler.name)
//        contentValues.put(COL_COUNTRY, sohbetler.country)
//        contentValues.put(COL_AGE, sohbetler.age)
//
//        val result = sqliteDB.insert(TABLE_NAME,null,contentValues)
//
//        Toast.makeText(context,if(result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.", Toast.LENGTH_SHORT).show()
//    }

    @SuppressLint("Range")
    fun readData():MutableList<ButunSohbetler>{



        val sohbetList = mutableListOf<ButunSohbetler>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val sohbetler= ButunSohbetler()
                sohbetler.Id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                sohbetler.Title = result.getString(result.getColumnIndex(COL_TITLE))
                sohbetler.Description = result.getString(result.getColumnIndex(COL_Description))
                sohbetler.VideoLink= result.getString(result.getColumnIndex(COL_VideoLink))

                sohbetList.add(sohbetler)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return sohbetList
    }



    fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME,null,null)
        sqliteDB.close()

    }

//    fun updateAge(age:Int) {
//        val db = this.writableDatabase
//        val query = "SELECT * FROM $TABLE_NAME"
//        val result = db.rawQuery(query,null)
//        if(result.moveToFirst()){
//            do {
//                val cv = ContentValues()
//                cv.put(COL_AGE,(result.getInt(result.getColumnIndex(COL_AGE))+age))
//                db.update(TABLE_NAME,cv, "$COL_ID=? AND $COL_NAME=?",
//                    arrayOf(result.getString(result.getColumnIndex(COL_ID)),
//                        result.getString(result.getColumnIndex(COL_NAME))))
//            }while (result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//    }

}