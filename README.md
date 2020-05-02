# NotesAppMVVM
Android Architecture Example. A Notes app made by using MVVM architecture with Room, LiveData, Navigation and ViewModel.

## Notes on Android Architectre Components 

1. Android Architectre Components are different bunch of libraries which help to build more robust and maintainable apps

2. When apps get biggere the tightly coupled code becomes problem (All code on single page is bad also called speghatie code)

3. Room persistance library: Takes care of most of the complicated stuff, provides compile time verfication for SQL statement, less boilrplate code
Room contains- entities(Java class) which represent table in sql db and Data Access Object (DAO)  which is used to communicate wit sqlite

4. ViewModel: this class has job to hold and prepare all data for the UI so we don't need to add any of it directly into our activities and fragments, instead activites and fragments
connect to this ViewModel and gets all the necessary data from there and then only has the job to draw it on the screen and reporting user interactions back to the ViewModel
the ViewModel then forwards these interactions to the underlying layers of the app either to load new data or to make changes to the data set so the ViewModel works as the Gateway
to the UI controller which is the activity or fragment to the rest of the app and we don't initiate ant database operations from our activity directly so the ativity itself doesn't know
what is going on down with Room. This way we keep our activity and fragment classes clean. Also ViewModel class receive device configuration changes eg: device rotated etc

5. Repository: This creates layer of abstraction and a single point of contact for ViewModel to interact with all the different data sets. Thus ViewModel doesn't neeed to worry
about from where the data is being fetched.

6. LiveData: LiveData is Observable. It is a wrapper that can hold any time of data including list and it can be observed by UI controller ie whenever the data is changed int LiveData object he UI controller
makes changes to view accordingly. Also LiveData is lifecycle awaire ie LiveData know when activit/fragment is background and foreground and accordingly pass data to it for
diplay, it also acts according to activity lifecycle ie resume stop and does all clean up job when  activity lifcycle end's. So livedata takes care of potential bugs and memory leaks problems.


## Links:

1.Android Architecture Guide: https://developer.android.com/jetpack/docs/guide

2. Defining data using Room entities: https://developer.android.com/training/data-storage/room/defining-data

3. Adding Components to your Project: https://developer.android.com/topic/libraries/architecture/adding-components

4. o know equivalent for new Androidx Artifacts from old build artifacts, please refer the below link: https://developer.android.com/jetpack/androidx/migrate/artifact-mappings
