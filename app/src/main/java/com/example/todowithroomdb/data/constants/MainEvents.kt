package com.example.todowithroomdb.data.constants

/**
 * Created by Himanshu Verma on 12/06/24.
 **/
sealed class MainEvents {
    data class OnItemClicked(val position: Int) : MainEvents()
}