package com.example.notes_architecture.presentation.ui.fragment.note

import com.example.notes_architecture.domain.model.Note
import com.example.notes_architecture.domain.usecases.DeleteNoteUseCase
import com.example.notes_architecture.domain.usecases.GetAllNotesUseCase
import com.example.notes_architecture.presentation.ui.base.BaseViewModel
import com.example.notes_architecture.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNotesState = _deleteNoteState.asStateFlow()

    fun deleteNote(note: Note) {
        deleteUseCase(note).collectData(_deleteNoteState)
    }

    fun getAllNotes() {
        getAllNotesUseCase().collectData(_getAllNotesState)
    }
}


