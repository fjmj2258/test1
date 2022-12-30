package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class lunch_SQL (
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = v
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private const val database = "myDatabase" //資料庫名稱
        private const val v = 1 //資料庫版本
    }

    override fun onCreate(db: SQLiteDatabase) {
        //建立 myTable 資料表，表內有 food 字串欄位123
        db.execSQL("CREATE TABLE lun(food text PRIMARY KEY)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //升級資料庫版本時，刪除舊資料表，並重新執行 onCreate()，建立新資料表
        db.execSQL("DROP TABLE IF EXISTS lun")
        onCreate(db)
    }
}