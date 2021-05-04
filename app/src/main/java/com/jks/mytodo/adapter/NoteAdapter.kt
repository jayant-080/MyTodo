package com.jks.mytodo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jks.mytodo.R
import com.jks.mytodo.entity.Note
import kotlinx.android.synthetic.main.notes_item.view.*

class NoteAdapter(val context: Context,private val listener:INotesRVAdapter): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

var allNotes = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_item,parent,false))

        viewHolder.itemView.tv_delete.setOnClickListener {
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun getItemCount(): Int {
      return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote =allNotes[position]
        holder.mytext.text = currentNote.text
    }

    fun updateTodo(newNote:List<Note>){
        allNotes.clear()
        allNotes.addAll(newNote)
        notifyDataSetChanged()
    }

    class NoteViewHolder(itemview:View) : RecyclerView.ViewHolder(itemview){
      val mytext : TextView = itemview.findViewById(R.id.tv_notes)
    }


    interface INotesRVAdapter {
        fun onItemClick(notes:Note)
    }
}