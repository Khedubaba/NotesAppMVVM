package com.adityakhedekar.khedubaba.notesappmvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*AndroidViewModel is a subclass of ViewModel and the difference between these two is in AndroidViewModel we get to pass application into
constructor which we can use for the another application into constructor which we can use for another application context if needed. You
should never store context of an activity or view that references an activity in the ViewModel as we learn ViewModel is design to out live
an activity after it's destroyed and if we hold reference to already destroyed activity we have memory leak problem. But we have to pass
context to our Repository because we need to instantiate our database instance and this is why we extend AndroidViewModel bcoz thus we can
get application context and pass it down to the database.
*/
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    //Our activity has only access to ViewModel and not to Repository thus here in ViewModel we create Repository methods for DB operations
    //methods from our repository
    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
