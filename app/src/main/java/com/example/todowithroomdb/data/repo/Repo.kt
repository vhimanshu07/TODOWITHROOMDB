package com.example.todowithroomdb.data.repo

import androidx.lifecycle.LiveData
import com.example.todowithroomdb.data.model.ListData

/**
 * Created by Himanshu Verma on 08/06/24.
 **/
interface Repo {

    suspend fun insertData(data: ListData)

    fun getData(): LiveData<List<ListData>>

    suspend fun deleteData(data: ListData)
}