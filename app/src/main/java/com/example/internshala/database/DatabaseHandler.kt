package com.example.internshala.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.internshala.models.WorkshopModel

class DatabaseHandler(context: Context,factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 2 // dataBAse version
        private const val DATABASE_NAME = "WorkshopDatabase" // database name
        private const val TABLE_WORKSHOP_DETAILS = "WorkshopTable" // table name

        //All the columns name
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_IMAGE = "image"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_MODE = "mode"
        private const val KEY_APPLIED = "isApplied"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createWorkshopTable = ("CREATE TABLE " + TABLE_WORKSHOP_DETAILS  + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_IMAGE  + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_MODE + " TEXT,"+
                KEY_APPLIED + " TEXT" +
                 ")")

        db?.execSQL(createWorkshopTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_WORKSHOP_DETAILS")
        onCreate(db)
    }
    fun addWorkshop(workshopModel: WorkshopModel) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE,workshopModel.title)
        contentValues.put(KEY_DESCRIPTION,workshopModel.description)
        contentValues.put(KEY_MODE,workshopModel.mode)
        contentValues.put(KEY_IMAGE,workshopModel.image)

        val result = db.insert(TABLE_WORKSHOP_DETAILS,null,contentValues)
        db.close()
     return result
    }
    fun updateWorkshop(workshopModel: WorkshopModel) : Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_APPLIED,workshopModel.isApplied)

        val success = db.update(TABLE_WORKSHOP_DETAILS,contentValues, KEY_ID + "=" + workshopModel.id,null)
        db.close()
        return success
    }
    fun deleteWorkshop(workshopModel: WorkshopModel) : Int{
        val db = this.writableDatabase

       val success =  db.delete(TABLE_WORKSHOP_DETAILS, KEY_ID + "=" + workshopModel.id,null)
        db.close()

        return success
    }
    fun getWorkshopList() : ArrayList<WorkshopModel>{

        val workshopList = ArrayList<WorkshopModel>()

        val selectQuery = "SELECT * FROM $TABLE_WORKSHOP_DETAILS"

        val db = this.readableDatabase

        try {
           val cursor = db.rawQuery(selectQuery,null)

            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                    val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                    val mode = cursor.getString(cursor.getColumnIndex(KEY_MODE))
                    val image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE))
                    val isApplied = cursor.getInt(cursor.getColumnIndex(KEY_APPLIED))

                    val place = WorkshopModel(id,title,description,mode,image,isApplied)
                    workshopList.add(place)

                }while (cursor.moveToNext())
            }
            cursor.close()

        }catch (e : SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        return workshopList
    }
    fun getAppliedWorkshopList() : ArrayList<WorkshopModel>{

        val workshopList = ArrayList<WorkshopModel>()

        val selectQuery = "SELECT * FROM $TABLE_WORKSHOP_DETAILS"

        val db = this.readableDatabase

        try {
            val cursor = db.rawQuery(selectQuery,null)

            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                    val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                    val mode = cursor.getString(cursor.getColumnIndex(KEY_MODE))
                    val image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE))
                    val isApplied = cursor.getInt(cursor.getColumnIndex(KEY_APPLIED))

                    if (isApplied == 1) {
                        val place = WorkshopModel(id, title, description, mode, image, isApplied)
                        workshopList.add(place)
                    }

                }while (cursor.moveToNext())
            }
            cursor.close()

        }catch (e : SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        return workshopList
    }
}
