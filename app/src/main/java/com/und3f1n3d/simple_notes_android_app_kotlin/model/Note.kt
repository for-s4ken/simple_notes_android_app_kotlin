package com.und3f1n3d.simple_notes_android_app_kotlin.model

class Note(textToSet: String) {

    var text: String = textToSet
    set(value){ field = value }
    get() = field

    fun changeText(change: String){
        text = change
    }

    override fun toString(): String {
        return text
    }
}