package com.example.todowithroomdb.data.repo

import androidx.lifecycle.LiveData
import com.example.todowithroomdb.data.model.ListData
import com.example.todowithroomdb.data.room.MyDatabase
import javax.inject.Inject

/**
 * Created by Himanshu Verma on 08/06/24.
 **/
class RepoImpl @Inject constructor(val database: MyDatabase) : Repo {
    override suspend fun insertData(data: ListData) {
        database.listDAO().insert(data)
    }

    override fun getData(): LiveData<List<ListData>> {
        return database.listDAO().getData()
    }

    override suspend fun deleteData(data: ListData) {
        database.listDAO().deleteData(data)
    }

}