package com.example.toy_project

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PersonDB_Test private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Phone.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "phone_test"
        const val COL1_ID = "_id"
        const val COL2_NAME = "name"
        const val COL3_PHONE = "phone"

        //SingleTon Pattern(싱글톤 패턴)
        @Volatile
        private var instance: PersonDB_Test? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(PersonDB_Test::class.java) {
                instance ?: PersonDB_Test(context).also {
                    instance = it
                }
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COL1_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL2_NAME TEXT, " +
                "$COL3_PHONE TEXT )"

        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    fun insertData(name: String, phone: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL2_NAME, name)
            put(COL3_PHONE, phone)
        }
        db.insert(TABLE_NAME, null, contentValues) // 값이 없으면 행을 삽입하지않음
    }

    fun updateData(id: String, name: String, phone: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL1_ID, id)
            put(COL2_NAME, name)
            put(COL3_PHONE, phone)
        }
        db.update(TABLE_NAME, contentValues, "$COL1_ID = ?", arrayOf(id))
    }

    fun getAllData(): String {
        var result = "No data in DB"

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        try {
            if (cursor.count != 0) {
                val stringBuffer = StringBuffer()
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID :" + cursor.getInt(0).toString() + "\n")
                    stringBuffer.append("NAME :" + cursor.getString(1) + "\n")
                    stringBuffer.append("PHONE :" + cursor.getString(2) + "\n")
                }
                result = stringBuffer.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return result
    }
}

/*class PersonDB_Test private  constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSON){
    private var db: SQLiteDatabase? = null

    companion object{
        private const val DATABASE_NAME = "person_list_test.db"
        private const val DATABASE_VERSON = 1

        private const val TABLE_NAME = "person_list"
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val PHONE = "PHONE"

        @Volatile
        private var instance: PersonDB_Test? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(PersonDB_Test::class.java) {
                instance ?: PersonDB_Test(context).also {
                    instance = it
                }
            }
    }

    //DB생성
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ( $ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NAME TEXT, $PHONE TEXT )"
        db?.execSQL(query);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1 != p2) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    fun insertData(name: String, phone: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(NAME, name)
            put(PHONE, phone)
        }
        db.insert(TABLE_NAME, null, contentValues)// 값이 없으면 행을 삽입하지않음
    }

    fun updateData(name: String, phone: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(NAME, name)
            put(PHONE, phone)
        }
        db.update(TABLE_NAME, contentValues, "$PHONE = ?", arrayOf(phone))
    }

    fun getAllData(): String {
        var result = "No data in DB"

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        try {
            if (cursor.count != 0) {
                val stringBuffer = StringBuffer()
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID :" + cursor.getInt(0).toString() + "\n")
                    stringBuffer.append("NAME :" + cursor.getString(1) + "\n")
                    stringBuffer.append("PHONE :" + cursor.getString(2) + "\n")
                }
                result = stringBuffer.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return result
    }
}*/