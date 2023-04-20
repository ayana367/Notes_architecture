package com.example.notes_architecture.data.base

import com.example.notes_architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {
    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(com.example.notes_architecture.domain.utils.Resource.Loading())
        try {
            emit(com.example.notes_architecture.domain.utils.Resource.Success(request()))
        } catch (ioException:IOException){
            emit(com.example.notes_architecture.domain.utils.Resource.Error(ioException.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}