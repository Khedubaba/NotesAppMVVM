package com.adityakhedekar.khedubaba.notesappmvvm;

//This class is a Entity class of room whic creates a note entity to be stored in sqlite db

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

//    @ColumnInfo(name = "priority_column")
//    @Ignore : use Ignore annotation if you don't want this fields to be added to tables
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
