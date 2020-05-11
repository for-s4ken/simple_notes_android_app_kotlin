package com.und3f1n3d.simple_notes_android_app_kotlin

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.und3f1n3d.simple_notes_android_app_kotlin.fragments.NoteEditFragment
import com.und3f1n3d.simple_notes_android_app_kotlin.fragments.NotesListFragment
import com.und3f1n3d.simple_notes_android_app_kotlin.model.Note
import java.io.File
import java.io.PrintWriter

private const val MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1337

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
        notes[id].text = textToReplace
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
        when(item.itemId){
            addItem.itemId -> changeFragment(NoteEditFragment())
            saveNotesItem.itemId -> exportNotes()
        }
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

    private fun changeFragment(fragmentToChange: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.mainLayout, fragmentToChange)
            .commit()
    }

    // firstly checking for user permission, then check if file is created

    private fun exportNotes(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val savedNotes = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/notes.txt")
            println(savedNotes.canonicalPath)
            savedNotes.createNewFile()
            val writer = PrintWriter(savedNotes)
            for(temp in notes){
                writer.println(temp)
            }
            writer.close()
            Toast.makeText(this, "Successfully exported to Downloads", Toast.LENGTH_LONG)
                .show()

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
            Toast.makeText(this, "Permission granted! Try again.", Toast.LENGTH_LONG).show()
        }
    }
}
