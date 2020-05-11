package com.und3f1n3d.simple_notes_android_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.und3f1n3d.simple_notes_android_app_kotlin.fragments.NotesListFragment
import com.und3f1n3d.simple_notes_android_app_kotlin.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var notes: ArrayList<Note>
    private lateinit var addItem: MenuItem
    private lateinit var saveNotesItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            notes = ArrayList()
            for(i: Int in 0..10){
                notes.add(Note("Note number $i"))
                println(notes[i].toString())
            }
            changeFragment(NotesListFragment())
        }

    }

    // notes editing

    fun editNote(id: Int, textToReplace: String){
        notes[id]
    }

    fun addNote(toAdd: Note){
        notes.add(toAdd)
    }

    //

    // overriding

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        addItem = menu.findItem(R.id.addItem)
        saveNotesItem = menu.findItem(R.id.saveNotesItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        return super.onOptionsItemSelected(item)
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
