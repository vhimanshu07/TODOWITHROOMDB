package com.example.todowithroomdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todowithroomdb.data.constants.MainEvents
import com.example.todowithroomdb.data.room.DAO
import com.example.todowithroomdb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel? = null

    private lateinit var adapter: Adapter

    @Inject
    lateinit var myDao: DAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel?.init()
        setUpRecyclerView()
        setObservers()
        setClickListeners()
    }

    private fun setUpRecyclerView() {
        adapter = Adapter(ArrayList(), object : callBack {
            override fun onItemClicked(position: Int) {
                openQuestionBottomSheet(position)
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun setObservers() {
        viewModel?.list?.observe(this) {
            if (it != null) {
                adapter.addData(ArrayList(it))
                viewModel?.updateLocalData(it)
            }
        }
        viewModel?.events = {
            when (it) {
                is MainEvents.OnItemClicked -> {
                    deleteItem(it.position)
                }
            }
        }
    }

    private fun deleteItem(position: Int) {
        adapter.deleteItem(position)
    }

    private fun setClickListeners() {
        binding.apply {
            addData.setOnClickListener {
                openAddNoteView()
            }
        }
    }

    private fun openAddNoteView() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.isDestroyed)
            return
        val fragment = DataEntryFragment.newInstance()
        fragment.show(fragmentManager, DataEntryFragment.TAG)
    }

    private fun openQuestionBottomSheet(position: Int) {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.isDestroyed)
            return
        val fragment = QuestionBottomSheet.newInstance()
        val bundle = Bundle()
        bundle.putInt(POSITION, position)
        fragment.arguments = bundle
        fragment.show(fragmentManager, QuestionBottomSheet.TAG)
    }

    companion object {
        const val POSITION = "pos"
    }
}