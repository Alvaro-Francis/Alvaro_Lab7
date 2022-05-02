package com.example.alvaro_lab7.controllers

import java.util.*

import com.example.alvaro_lab7.models.questions.AllQuestions


class NextQuestion {

    var allQuestions: AllQuestions = AllQuestions()

    var question: Int = 0
    val total_qs: Int = allQuestions.numberOfQuestions()


    public fun linearNextQuestion(): Int {
        question = (question + 1) % total_qs
        //return allQuestions.allQuestions[question].index
        return 1
    }

    public fun pseudoRandomNextQuestion(): Int {
        val random = Random()
        question = random.nextInt(total_qs)

        //return allQuestions.allQuestions[question].index
        return 1
    }

    fun isRight(status: Boolean) : Boolean {
        return status
    }




}