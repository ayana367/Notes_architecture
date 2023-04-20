package com.example.notes_architecture.domain.model

import java.io.Serializable

class Note (
    val id: Int = DEFAULT_ID,
    var title: String = "",
    var descriptions: String = ""
) : Serializable {
    companion object{
        const val DEFAULT_ID = 0
    }
}