package com.example.notes_architecture.presentation.ui.fragment.add_note

import com.example.notes_architecture.domain.model.Note
import com.example.notes_architecture.domain.usecases.CreateNoteUseCase
import com.example.notes_architecture.domain.usecases.EditNoteUseCase
import com.example.notes_architecture.presentation.ui.base.BaseViewModel
import com.example.notes_architecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val createNoteUseCase: com.example.notes_architecture.domain.usecases.CreateNoteUseCase,
    private val editNoteUseCase: com.example.notes_architecture.domain.usecases.EditNoteUseCase,
) : BaseViewModel() {

    private val _addNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val addNoteState = _addNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note: com.example.notes_architecture.domain.model.Note) {
        createNoteUseCase(note).collectData(_addNoteState)
    }

    fun editNote(note: com.example.notes_architecture.domain.model.Note) {
        editNoteUseCase(note).collectData(_editNoteState)
    }
}