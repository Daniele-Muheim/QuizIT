package ch.hslu.mobpro.QuizIT.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.hslu.mobpro.QuizIT.Constants
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.databinding.FragmentResultBinding


class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var viewBinding: FragmentResultBinding
    private  val binding get() = viewBinding
    private var correctAnswers = 0
    private var totalQuestions = 0

    companion object {
        @JvmStatic
        fun newInstance(username: String, correctAnswers: Int, totalQuestions: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.USER_NAME, username)
                    putInt(Constants.CORRECT_ANSWERS, correctAnswers)
                    putInt(Constants.TOTAL_QUESTIONS, totalQuestions)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewBinding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.username.text = it.getString(Constants.USER_NAME)
            totalQuestions = it.getInt(Constants.TOTAL_QUESTIONS)
            correctAnswers = it.getInt(Constants.CORRECT_ANSWERS)
        }

        viewBinding.retry.setOnClickListener { clickRetryButton() }
        viewBinding.correctAnswers.text = "$correctAnswers von $totalQuestions Antworten richtig"

    }

    private fun clickRetryButton() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.hostFragment, HostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}