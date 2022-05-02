package com.example.alvaro_lab7.models.questions

import com.example.alvaro_lab7.models.questions.Answer
import com.example.alvaro_lab7.models.questions.AnswerList

class Question  {
    lateinit var answers: AnswerList
    lateinit var question: String
    lateinit var image: String

    constructor (answers: AnswerList, question: String, image: String) {
        this.answers = answers
        this.question = question
        this.image = image

    }

    constructor() {}
}