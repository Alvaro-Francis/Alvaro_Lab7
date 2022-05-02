package com.example.alvaro_lab7.models.questions

import kotlin.properties.Delegates

class Answer () {
    lateinit var answerString : String
    var isTrue by Delegates.notNull<Boolean>()

    constructor(answerString: String, isTrue: Boolean) : this() {
        this.answerString = answerString
        this.isTrue = isTrue
    }
}
