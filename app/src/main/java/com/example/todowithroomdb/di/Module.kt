package com.example.todowithroomdb.di

import android.content.Context
import com.example.todowithroomdb.data.room.DAO
import com.example.todowithroomdb.data.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


/**
 * Created by Himanshu Verma on 08/06/24.
 **/
@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesDataBase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getDataBase(context)
    }

    @Provides
    fun providesDAO(myDatabase: MyDatabase): DAO {
        return myDatabase.listDAO()
    }

}