package com.und3f1n3d.simple_notes_android_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.und3f1n3d.simple_notes_android_app_kotlin.fragments.NotesListFragment
import com.und3f1n3d.simple_notes_android_app_kotlin.model.Note

class MainActivity : AppCompatActivity() {

    private var notes: ArrayList<Note> =  ArrayList()
    private var addItem: MenuItem? = null
    private var saveNotesItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            for(i: Int in 0..10){
                notes.add(Note("Note number $i"))
                println(notes[i].toString())
            }
            changeFragment(NotesListFragment())
        }

    }

    // notes editing

    fun editNote(id: Int, textToReplace: String){
        notes[id].changeText(textToReplace)
    }

    fun addNote(toAdd: Note){
        notes.add(toAdd)
    }

    //

    //overriding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        addItem = menu?.findItem(R.id.addItem)
        saveNotesItem = menu?.findItem(R.id.saveNotesItem)
        return true
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0){
            super.onBackPressed()
        }else{
            supportFragmentManager.popBackStack()
        }
    }

    //

    fun changeFragment(fragmentToChange: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.mainLayout, fragmentToChange)
            .commit()
    }
}
