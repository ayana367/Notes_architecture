package com.example.notes_architecture.presentation.ui.fragment.add_note

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notes_architecture.R
import com.example.notes_architecture.databinding.FragmentAddEditNotesBinding
import com.example.notes_architecture.domain.model.Note
import com.example.notes_architecture.presentation.ui.base.BaseFragment
import com.example.notes_architecture.presentation.ui.fragment.note.NotesFragment.Companion.NOTE
import com.example.notes_architecture.presentation.utils.UIState
import com.example.notes_architecture.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditNotesFragment : BaseFragment(R.layout.fragment_add_edit_notes) {

    private val viewModel: AddNoteViewModel by viewModels()
    private val binding by viewBinding(FragmentAddEditNotesBinding::bind)
    private var note: Note? = null
    private var noteIsNull = true

    override fun setupRequests() {
        addEdit()
    }

    override fun initSubscriber() {
        setupSubscribers()
        collectEditNote()
    }

    @SuppressLint("SetTextI18n")
    private fun getNotes() {
        if (arguments?.getSerializable(NOTE) == null) {
            note = Note()
        } else {
            note = arguments?.getSerializable(NOTE) as Note
            binding.etTitle.setText(note!!.title)
            binding.etDesc.setText(note!!.descriptions)
            binding.btnSave.text = "Edit"
            noteIsNull = false
        }
    }

    private fun addEdit() {
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.etTitle.text!!.isNotEmpty() && binding.etDesc.text!!.isNotEmpty()) {
                    note?.title = etTitle.text.toString()
                    note?.descriptions = etDesc.text.toString()
                    if (noteIsNull) {
                        viewModel.createNote(note!!)
                    } else {
                        viewModel.editNote(note!!)
                    }
                } else {
                    context?.showToast("Поля не должны быть пустыми")
                }
            }
        }
    }

    override fun setupSubscribers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun initialize() {
        getNotes()
    }

    private fun collectEditNote() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}