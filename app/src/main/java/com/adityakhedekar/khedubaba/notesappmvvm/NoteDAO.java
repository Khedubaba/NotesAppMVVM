package com.adityakhedekar.khedubaba.notesappmvvm;

//DAO's need to be interfaces or abstract classes as we don't provide method body we just create methods and antate it and Room will
// generate all the necesaary code for it. General rule is create 1 DAO per entity

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
