package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ch.hslu.mobpro.QuizIT.QuizViewModel
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.databinding.FragmentHostBinding

class HostFragment : Fragment(R.layout.fragment_host) {
    private lateinit var viewBinding: FragmentHostBinding
    private  val binding get() = viewBinding
    private val quizViewModel: QuizViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() : HostFragment {
            return HostFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewBinding = FragmentHostBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.startButton.setOnClickListener { clickStartButton() }
        viewBinding.leaderboardButton.setOnClickListener { clickLeaderboardButton() }
        viewBinding.textboxName.setText(quizViewModel.getUsername())

    }
    private fun clickStartButton() {
        if (viewBinding.textboxName.text.toString().isEmpty()) {
            Toast.makeText(requireActivity(), "Bitte gib einen Namen ein", Toast.LENGTH_SHORT).show()
        } else {
            quizViewModel.setUsername(viewBinding.textboxName.text.toString())
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.hostFragment, QuizQuestionsFragment.newInstance(binding.textboxName.text.toString()))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun clickLeaderboardButton () {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.hostFragment, LeaderboardFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}