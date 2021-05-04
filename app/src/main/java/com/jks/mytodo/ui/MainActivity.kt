package com.jks.mytodo.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jks.mytodo.R
import com.jks.mytodo.adapter.NoteAdapter
import com.jks.mytodo.entity.Note
import com.jks.mytodo.viewmodals.NotesViewModal
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , NoteAdapter.INotesRVAdapter{

    lateinit var notesViewModal: NotesViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = NoteAdapter(this,this)
        rv_notes.adapter = adapter
        notesViewModal= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModal::class.java)
        notesViewModal.allNotes.observe(this, Observer {list->
           if(list.isEmpty()){
             rv_notes.visibility= View.INVISIBLE
              lt_dummy.visibility = View.VISIBLE
           }else{
               rv_notes.visibility = View.VISIBLE
               lt_dummy.visibility = View.INVISIBLE
               adapter.updateTodo(list)
           }
            list?.let {
                rv_notes.visibility = View.VISIBLE
                adapter.updateTodo(it)
            }
        })


        btn_add.setOnClickListener {
            val itext=et_notes.text.toString()
            if(itext.isNotEmpty()){
                notesViewModal.insertNotes(Note(itext))
                et_notes.setText("")
            }else{
                Toast.makeText(this,"field is empty", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onItemClick(notes: Note) {
        notesViewModal.deleteNotes(notes)
    }
}