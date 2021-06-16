package com.example.notes

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteRoomDatabase:RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    companion object{
        @Volatile
        private var INSTANCE:NoteRoomDatabase? = null
        fun getDataBase(context:Context):NoteRoomDatabase{
            //return INSTANCE if it is not null,else create database
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}