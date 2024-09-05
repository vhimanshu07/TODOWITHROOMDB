package com.example.todowithroomdb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todowithroomdb.data.model.ListData

/**
 * Created by Himanshu Verma on 08/06/24.
 **/


@Database(entities = [ListData::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun listDAO(): DAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDataBase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }
}