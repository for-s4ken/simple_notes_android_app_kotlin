package com.und3f1n3d.simple_notes_android_app_kotlin.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.und3f1n3d.simple_notes_android_app_kotlin.R

class NotesListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_notes_list, container, false)
        // Operations with rootView
        return rootView
    }


}
