package com.example.todowithroomdb.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todowithroomdb.databinding.FragmentDataEntryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DataEntryFragment : BottomSheetDialogFragment() {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    lateinit var binding: FragmentDataEntryBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDataEntryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            text.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    textLayout.isErrorEnabled = false
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            submitBtn.setOnClickListener {
                if (text.text.isNullOrEmpty()) {
                    textLayout.isErrorEnabled = true
                    textLayout.error = "Please enter note to continue"
                } else {
                    viewModel.noteAdded(text.text.toString())
                    dismiss()
                }
            }
        }
    }


    companion object {

        const val TAG: String = "DataEntry"

        fun newInstance() =
            DataEntryFragment()
    }
}