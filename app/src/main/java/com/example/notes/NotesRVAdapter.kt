package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notes_items.view.*

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapterClickListener):RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
     private val allNotes = ArrayList<Note>()
   inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView:TextView = itemView.text_tv
       val deleteButton:ImageView = itemView.delete_btn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val view = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_items,parent,false))
        view.deleteButton.setOnClickListener {
            listener.onItemClick(allNotes[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int  = allNotes.size
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapterClickListener{
    fun onItemClick(note:Note)
}