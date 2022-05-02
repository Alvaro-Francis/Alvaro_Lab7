package com.example.alvaro_lab7

import android.util.Log
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.alvaro_lab7.controllers.NextQuestion
import com.example.alvaro_lab7.models.questions.AllQuestions
import com.example.alvaro_lab7.models.questions.Answer
import com.example.alvaro_lab7.models.questions.AnswerList
import com.example.alvaro_lab7.models.questions.Question
import org.json.JSONArray
import org.json.JSONObject
import android.widget.Toast
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var getDataButton: Button
    private lateinit var basicQuestionView: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttons : Array<Button>
    private lateinit var scoreView : TextView

    private var nextQuestion : NextQuestion = NextQuestion()

    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private var points = 0
    val urlJSON = "http://192.168.50.95:8080/questions"

    var answeredQuestions = 0

    var correct1 = false
    var correct2 = false
    var correct3 = false
    var correct4 = false

    var right = mutableListOf(correct1, correct2, correct3, correct4)
    val urlIMAGE = "http://192.168.50.95:8080/static/"
    /*
    var allQuestions = AllQuestions()

    var question: Question = Question()

    var answerList = AnswerList()
    */
    var current = 0
    lateinit var json_contact: JSONObject

    fun getJSON() {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)


        val jsonArrayRequest = JsonObjectRequest(Request.Method.GET,
            urlJSON,
            null,
            { response ->
                json_contact = JSONObject(response.toString())
                var jsonarray_questions: JSONArray = json_contact.getJSONArray("questions")
                var allQuestions = AllQuestions()
                var i = 0
                var size: Int = jsonarray_questions.length()
                Log.d("SizeSuccess", "$size")
                for (i in 0 until size) {
                    var question: Question = Question()

                    var json_question: JSONObject = jsonarray_questions.getJSONObject(i)
                    question.question = json_question.getString("question")
                    var answerString =json_question.getString( "answers")
                    //get the answers and put them in a list
                    var answerList = AnswerList()
                    val json_answers: JSONArray = JSONArray(answerString)
                    var answerSize = json_answers.length()
                    var j = 0
                    for (j in 0 until answerSize) {
                        var answer: Answer = Answer()
                        var json_answer = json_answers.getJSONObject(j)
                        answer.answerString = json_answer.getString("answer")
                        answer.isTrue = json_answer.getBoolean("isTrue")
                        answerList.addAnswer(answer)
                    }
                    question.answers = answerList
                    question.image = json_question.getString("image")
                    allQuestions.addQuestion(question)
                    nextQuestion.allQuestions.addQuestion(question)
                }
                // Display the first 500 characters of the response string.

                Log.d("JSuccess", "$response")
                Log.d("QSuccess", "This message means that you have put questions in the right data object:" +
                        nextQuestion.allQuestions.printQuestions())
                Log.d("nextQuestionSize", "${nextQuestion.allQuestions.allQuestions.size}")
                Log.d("AllQuestionSize", "${allQuestions.allQuestions.size}")

                //get the image from the json
                getImage("$urlIMAGE${allQuestions.allQuestions[current].image}")

                //we set up the layout here because all of the necessary data is in this block of code
                basicQuestionView.text = nextQuestion.allQuestions.allQuestions[current].question
                //set the booleans for all the right answers
                Log.d("Size", "${nextQuestion.allQuestions.allQuestions[current].answers.numberOfAnswers()}")
                for (i in 0 until allQuestions.numberOfQuestions() - 1) {
                    right[i] = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).isTrue
                    buttons[i].text = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).answerString
                }


                button.setOnClickListener {
                    if (current == 5) {
                        try {
                            basicQuestionView.setText("You are done with this Quiz")
                        }
                        catch(err: java.lang.IndexOutOfBoundsException) {
                            Toast.makeText(baseContext, "You Reached the end", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        current += 1
                        if(nextQuestion.isRight(right[0])) {
                            points += 1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Right", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            points -=1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Wrong", Toast.LENGTH_SHORT).show()
                        }
                        //current += 1
                        basicQuestionView.setText(nextQuestion.allQuestions.allQuestions[current].question)
                        for (i in 0 until nextQuestion.allQuestions.allQuestions[current].answers.numberOfAnswers()) {
                            right[i] = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).isTrue
                            buttons[i].text = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).answerString
                        }
                        getImage("$urlIMAGE${allQuestions.allQuestions[current].image}")

                    }
                }

                button2.setOnClickListener {
                    if (current == 5) {
                        try {
                            basicQuestionView.setText("You are done with this Quiz")
                        }
                        catch(err: java.lang.IndexOutOfBoundsException) {
                            Toast.makeText(baseContext, "You Reached the end", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        current += 1
                        if(nextQuestion.isRight(right[1])) {
                            points += 1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Right", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            points -=1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Wrong", Toast.LENGTH_SHORT).show()
                        }
                        //current += 1
                        basicQuestionView.setText(nextQuestion.allQuestions.allQuestions[current].question)
                        for (i in 0 until nextQuestion.allQuestions.allQuestions[current].answers.numberOfAnswers()) {
                            right[i] = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).isTrue
                            buttons[i].text = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).answerString
                        }

                    }
                }

                button3.setOnClickListener {
                    if (current == 5) {
                        try {
                            basicQuestionView.setText("You are done with this Quiz")
                        }
                        catch(err: IndexOutOfBoundsException) {
                            Toast.makeText(baseContext, "You Reached the end", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        current += 1
                        if(nextQuestion.isRight(right[2])) {
                            points += 1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Right", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            points -=1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Wrong", Toast.LENGTH_SHORT).show()
                        }
                        //current += 1
                        basicQuestionView.setText(nextQuestion.allQuestions.allQuestions[current].question)
                        for (i in 0 until nextQuestion.allQuestions.allQuestions[current].answers.numberOfAnswers()) {
                            right[i] = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).isTrue
                            buttons[i].text = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).answerString
                        }

                    }
                }

                button4.setOnClickListener {
                    if (current == 5) {
                        try {
                            basicQuestionView.setText("You are done with this Quiz")
                        }
                        catch(err: java.lang.IndexOutOfBoundsException) {
                            Toast.makeText(baseContext, "You Reached the end", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        current += 1
                        if(nextQuestion.isRight(right[3])) {
                            points += 1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Right", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            points -=1
                            scoreView.setText("$points")
                            Toast.makeText(baseContext, "Wrong", Toast.LENGTH_SHORT).show()
                        }
                        //current += 1
                        basicQuestionView.setText(nextQuestion.allQuestions.allQuestions[current].question)
                        for (i in 0 until nextQuestion.allQuestions.allQuestions[current].answers.numberOfAnswers()) {
                            right[i] = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).isTrue
                            buttons[i].text = nextQuestion.allQuestions.allQuestions[current].answers.getAnswer(i).answerString
                        }

                    }
                }

            },
            { error -> Log.d("JFail", "${error}") })

        queue.add(jsonArrayRequest)

    }

    fun getImage(url: String) {
        val queue = Volley.newRequestQueue(this)
        val imageRequest = ImageRequest(
            url,
            { response: Bitmap ->
                // Display the first 500 characters of the response string.
                imageView.setImageBitmap(response)
            },
            0, 0,
            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

            { error -> Log.d("IFail", "Your error is $error") })

        queue.add(imageRequest)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataButton = findViewById(R.id.get_data_button)
        basicQuestionView = findViewById(R.id.basic_question_view)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        buttons = arrayOf(button, button2, button3, button4)
        scoreView = findViewById(R.id.score)
        scoreView.setText("${points}")

        getDataButton.setOnClickListener {
            getJSON()
// ...


            /*getDataButton.setOnClickListener {
        }*/
        }


    }
}