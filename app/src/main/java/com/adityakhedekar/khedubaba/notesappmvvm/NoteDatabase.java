package com.adityakhedekar.khedubaba.notesappmvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //we create this variable because we have to turn this class into singleton
    //Singleton means we can't create multiple instances of this database instead we use this same instance everywhere, which you can access over static variable
    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    //singelton
    //synchronized only one thread at time can access this method, so you don't accidentally two instances of db when two diff threads tries to acces this instance at a same time
    public static synchronized NoteDatabase getInstance(Context context){
        //we only want to instantiate this database only when there is no instance is available
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    //To populate default values in tables whenever Database is created
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDAO noteDAO;

        private PopulateDBAsyncTask(NoteDatabase db){
            noteDAO = db.noteDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Title 1", "Description 1", 1));
            noteDAO.insert(new Note("Title 2", "Description 2", 2));
            noteDAO.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }

}
