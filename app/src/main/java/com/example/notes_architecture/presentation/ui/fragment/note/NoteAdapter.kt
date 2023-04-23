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
    private var onClick: (Note) -> Unit,
    private var onLongClick: (Note) -> Unit,
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffUtilNoteItemCallback()) {


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
        fun onBind(note: Note) {
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
    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
}