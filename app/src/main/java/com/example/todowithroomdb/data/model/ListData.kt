package com.example.todowithroomdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Himanshu Verma on 08/06/24.
 **/

@Entity("listOfWork")
data class ListData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int? = null,
    @ColumnInfo("noteData")
    var note: String? = null,
)
