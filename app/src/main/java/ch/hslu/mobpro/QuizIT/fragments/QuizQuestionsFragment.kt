package ch.hslu.mobpro.QuizIT.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ch.hslu.mobpro.QuizIT.Constants
import ch.hslu.mobpro.QuizIT.Question
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.databinding.FragmentQuizQuestionsBinding


class QuizQuestionsFragment : Fragment(R.layout.fragment_quiz_questions), View.OnClickListener {

    private lateinit var viewBinding: FragmentQuizQuestionsBinding
    private val binding get() = viewBinding
    private var questionList: ArrayList<Question>? = null
    private var currentQuestionPosition = 1
    private var selectedAnswerPosition = 0
    private var correctAnswersCounter = 0
    private var username: String? = null

    companion object {
        @JvmStatic
        fun newInstance(username: String) =
            QuizQuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.USER_NAME, username)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewBinding = FragmentQuizQuestionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.questionList = Constants.getQuestions()

        arguments?.let {
            username = it.getString(Constants.USER_NAME)
        }

        setQuestions()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        viewBinding.answerOne.setOnClickListener(this)
        viewBinding.answerTwo.setOnClickListener(this)
        viewBinding.answerThree.setOnClickListener(this)
        viewBinding.answerFour.setOnClickListener(this)
        viewBinding.submitButton.setOnClickListener { clickSubmitButton() }
        viewBinding.abortButton.setOnClickListener { clickAbortButton() }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions() {
        val question: Question = this.questionList!![currentQuestionPosition - 1]
        setDefaultAnswerView()

        viewBinding.submitButton.text = "Überprüfen"
        viewBinding.progressbar.progress = currentQuestionPosition
        viewBinding.progressbarText.text = "$currentQuestionPosition" + "/" + viewBinding.progressbar.max
        viewBinding.questionId.text = question.question
        viewBinding.answerOne.text = question.answerOne
        viewBinding.answerTwo.text = question.answerTwo
        viewBinding.answerThree.text = question.answerThree

        if (question.answerFour == "") {
            viewBinding.answerFour.isClickable = false
            viewBinding.answerFour.setBackgroundColor(Color.WHITE)
            viewBinding.answerFour.text = ""
        } else {
            viewBinding.answerFour.text = question.answerFour
        }
    }

    private fun setDefaultAnswerView() {
        val answers = ArrayList<TextView>()
        val question: Question = this.questionList!![currentQuestionPosition - 1]
        answers.add(0, viewBinding.answerOne)
        answers.add(1, viewBinding.answerTwo)
        answers.add(2, viewBinding.answerThree)

        if (question.answerFour != "") {
            answers.add(3, viewBinding.answerFour)
        }

        for (answer in answers) {
                answer.setTextColor(Color.parseColor("#000000"))
                answer.typeface = Typeface.DEFAULT
                answer.background = view?.let {
                    ContextCompat.getDrawable(
                        it.context, R.drawable.default_answer_stroke_bg,
                    )
                }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.answer_one -> {
                selectedAnswer(viewBinding.answerOne, 1)
            }
            R.id.answer_two -> {
                selectedAnswer(viewBinding.answerTwo, 2)
            }
            R.id.answer_three -> {
                selectedAnswer(viewBinding.answerThree, 3)
            }
            R.id.answer_four -> {
                selectedAnswer(viewBinding.answerFour, 4)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private  fun clickSubmitButton() {
        if (selectedAnswerPosition == 0 && viewBinding.submitButton.text != "Überprüfen") {
            currentQuestionPosition++

            when{
                currentQuestionPosition <= questionList!!.size -> {
                    setQuestions()
                } else -> {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.hostFragment, ResultFragment.newInstance(username.toString(), correctAnswersCounter, questionList!!.size))
                    .addToBackStack(null)
                    .commit()
                }
            }
        } else if(selectedAnswerPosition != 0) {
            val question = questionList?.get(currentQuestionPosition -1)
            if (question!!.correctAnswer != selectedAnswerPosition) {
                checkAnswerView(selectedAnswerPosition, R.drawable.wrong_answer_stroke_bg)
            } else {
                correctAnswersCounter++
                checkAnswerView(question.correctAnswer, R.drawable.correct_answer_stroke_bg)
            }

            if (currentQuestionPosition == questionList!!.size){
                viewBinding.submitButton.text = "BEENDEN"
            } else {
                viewBinding.submitButton.text = "NÄCHSTE FRAGE"
            }
            selectedAnswerPosition = 0

        } else if (selectedAnswerPosition == 0){
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Wählen Sie eine Antwort aus!")
                .setCancelable(false)
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }

            val alert = dialogBuilder.create()
            alert.setTitle("Antwort")
            alert.show()
        }
    }

    private fun clickAbortButton() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("Wollen Sie das Quiz wirklich abbrechen")
            .setCancelable(true)
            .setPositiveButton("Ja") { _, _ ->
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.hostFragment, HostFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }

        dialogBuilder.setNegativeButton("Nein") { dialog, _ ->
            dialog.cancel()
        }
        val alert = dialogBuilder.create()
        alert.setTitle("Abbruch")
        alert.show()
    }

    private fun checkAnswerView(answer: Int, drawablesView: Int) {
        when(answer){
            1 -> {
                viewBinding.answerOne.background = view?.let {
                    ContextCompat.getDrawable(
                        it.context, drawablesView
                    )
                }
            }
            2 -> {
                viewBinding.answerTwo.background = view?.let {
                    ContextCompat.getDrawable(
                        it.context, drawablesView
                    )
                }
            }
            3 -> {
                viewBinding.answerThree.background = view?.let {
                    ContextCompat.getDrawable(
                        it.context, drawablesView
                    )
                }
            }
            4 -> {
                viewBinding.answerFour.background = view?.let {
                    ContextCompat.getDrawable(
                        it.context, drawablesView
                    )
                }
            }
        }
    }

    private fun selectedAnswer(tv: TextView, selectOptionNum: Int){
        setDefaultAnswerView()

        selectedAnswerPosition = selectOptionNum
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = view?.let {
            ContextCompat.getDrawable(
                it.context, R.drawable.selected_option_stroke_bg
            )
        }
    }

}
