package com.example.todowithroomdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todowithroomdb.data.constants.MainEvents
import com.example.todowithroomdb.data.model.ListData
import com.example.todowithroomdb.data.repo.RepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Himanshu Verma on 08/06/24.
 **/
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: RepoImpl,
) : ViewModel() {

    var events: ((MainEvents) -> Unit)? = null
    private lateinit var listData: LiveData<List<ListData>>

    private lateinit var listOfData: ArrayList<ListData>
    val list: LiveData<List<ListData>>
        get() = listData


    fun init() {
        listData = repo.getData()
        listOfData = ArrayList<ListData>()
    }

    private fun addData(newData: ListData) {
        viewModelScope.launch {
            repo.insertData(newData)
        }
        listOfData.add(newData)
    }


    fun deleteNote(position: Int?) {
        position?.let {
            events?.invoke(MainEvents.OnItemClicked(it))
            val itemData = listOfData[it]
            viewModelScope.launch {
                repo.deleteData(itemData)
            }
            listOfData.removeAt(position)
        }

    }

    fun noteAdded(note: String) {
        addData(ListData(note = note))
    }

    fun updateLocalData(it: List<ListData>) {
        listOfData.addAll(it)
    }
}