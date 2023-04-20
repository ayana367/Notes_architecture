package com.example.notes_architecture.presentation.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_architecture.databinding.ItemNotesBinding
import com.example.notes_architecture.domain.model.Note


class NoteAdapter(
    private var onClick: (com.example.notes_architecture.domain.model.Note) -> Unit,
    private var onLongClick: (com.example.notes_architecture.domain.model.Note) -> Unit,
) : ListAdapter<com.example.notes_architecture.domain.model.Note, NoteAdapter.NoteViewHolder>(DiffUtilNoteItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class NoteViewHolder(
        private val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: com.example.notes_architecture.domain.model.Note) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.descriptions
            itemView.setOnClickListener {
                onClick(note)
            }
            itemView.setOnLongClickListener {
                onLongClick(note)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    @SuppressLint("DiffUtilEquals")
    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<com.example.notes_architecture.domain.model.Note>() {
        override fun areItemsTheSame(oldItem: com.example.notes_architecture.domain.model.Note, newItem: com.example.notes_architecture.domain.model.Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.example.notes_architecture.domain.model.Note, newItem: com.example.notes_architecture.domain.model.Note): Boolean {
            return oldItem == newItem
        }

    }
}