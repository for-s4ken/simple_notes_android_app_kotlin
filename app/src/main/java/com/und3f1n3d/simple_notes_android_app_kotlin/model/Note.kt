package com.und3f1n3d.simple_notes_android_app_kotlin.model

class Note(textToSet: String) {

    var text: String = textToSet

    override fun toString(): String {
        return text
    }
}