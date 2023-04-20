package com.example.notes_architecture.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes_architecture.domain.utils.Resource
import com.example.notes_architecture.presentation.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<com.example.notes_architecture.domain.utils.Resource<T>>.collectData(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectData.collect { res ->
                when (res) {
                    is com.example.notes_architecture.domain.utils.Resource.Error -> {
                        if (res.message != null) {
                            _state.value = UIState.Error(res.message!!)
                        }
                    }
                    is com.example.notes_architecture.domain.utils.Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is com.example.notes_architecture.domain.utils.Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data!!)
                        }
                    }
                }
            }
        }
    }
}


