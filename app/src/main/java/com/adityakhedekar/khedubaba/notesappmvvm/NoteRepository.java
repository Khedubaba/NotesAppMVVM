package com.adityakhedekar.khedubaba.notesappmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();
    }

    //All this database operations methods are the API that Repository exposes to the outside, so ViewModel only has to call all these
    //methods without caring what is happening inside, and how data is fetched with different AsyncTask, thus creating the abstraction
    //layer by Repository
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }

    public void deleteAllNotes(Note note){
        new DeleteAllNoteAsyncTask(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


    //Unlike LiveData which room handles in the background the other operations need to be manually assigned to background thread
    //as on main thread it may crash the app thus we create a AsyncTask for each of them database operations and run it in background

    //static coz we can't allow direct reference to the database to avoid memory leak
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDAO noteDAO;

        //since class is static we cannot access noteDAO of our repository directly hence we pass it into constructor
        private InsertNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDAO noteDAO;

        //since class is static we cannot access noteDAO of our repository directly hence we pass it into constructor
        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDAO noteDAO;

        //since class is static we cannot access noteDAO of our repository directly hence we pass it into constructor
        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDAO noteDAO;

        //since class is static we cannot access noteDAO of our repository directly hence we pass it into constructor
        private DeleteAllNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... notes) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }

}
