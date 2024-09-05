package com.example.todowithroomdb.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todowithroomdb.data.model.ListData

/**
 * Created by Himanshu Verma on 08/06/24.
 **/
@Dao
interface DAO {

    /**
     *
     * C
     * R
     * U
     * D
     *
     */

    @Insert
    suspend fun insert(item: ListData)


    @Delete
    suspend fun deleteData(data: ListData)

    @Query("Select * from listOfWork")
    fun getData(): LiveData<List<ListData>>

    @Update
    suspend fun updateData(movie: ListData)

    @Query("delete from listOfWork where id =:id")
    suspend fun deleteId(id: Int)

    @Query("delete from listOfWork where noteData =:note")
    suspend fun deleteNote(note:String)

    @Query("delete from listOfWork")
    suspend fun deleteAll()
}