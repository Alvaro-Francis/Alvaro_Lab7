package com.example.alvaro_lab7.models.questions


class AnswerList() {
    private var answerList = mutableListOf<Answer>()

    constructor(answerList: List<Answer>) : this() {
        this.answerList = answerList.toMutableList()
    }


    fun numberOfAnswers(): Int {
        return answerList.size
    }

    fun setAnswerList(aList: List<Answer>) {
        answerList = aList.toMutableList()
    }

    fun getAnswer(index: Int) : Answer {
        return answerList[index]
    }

    fun addAnswer(answer: Answer) {
        answerList.add(answer)
    }
}