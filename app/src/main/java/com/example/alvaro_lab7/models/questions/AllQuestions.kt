package com.example.alvaro_lab7.models.questions

import android.util.Log
class AllQuestions {
    var allQuestions = mutableListOf<Question>()

    fun addQuestion(question: Question) {
        allQuestions.add(question)
    }

    fun numberOfQuestions() : Int {
        return allQuestions.size
    }

    fun printQuestions() {
        var i = 0
        for (i in 0 until allQuestions.size-1) {
            Log.d("Questions", "${allQuestions[i].question} ${allQuestions[i].answers} ${allQuestions[i].image}" )
        }
    }



}