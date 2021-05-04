package com.jks.mytodo.repository

import androidx.lifecycle.LiveData
import com.jks.mytodo.dao.NotesDao
import com.jks.mytodo.entity.Note


class NoteRepository (val notesDao: NotesDao) {


    val allNotes : LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insertNotes(note: Note) {

        notesDao.insertNotes(note)
    }

    suspend fun deleteNotes(note: Note){

        notesDao.deleteNotes(note)
    }

}