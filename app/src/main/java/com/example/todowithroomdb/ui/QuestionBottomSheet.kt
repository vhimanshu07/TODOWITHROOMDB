package com.example.todowithroomdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todowithroomdb.databinding.FragmentQuestionBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class QuestionBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentQuestionBottomSheetBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private var position: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuestionBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = arguments?.getInt(MainActivity.POSITION)
        binding.apply {
            okBtn.setOnClickListener {
                viewModel.deleteNote(position)
                dismiss()
            }
            cancelBtn.setOnClickListener {
                dismiss()
            }
        }

    }

    companion object {

        const val TAG: String = "Question"

        @JvmStatic
        fun newInstance() =
            QuestionBottomSheet()
    }
}