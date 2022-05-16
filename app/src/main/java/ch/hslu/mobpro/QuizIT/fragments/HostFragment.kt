package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.databinding.FragmentHostBinding

class HostFragment : Fragment(R.layout.fragment_host) {
    private lateinit var viewBinding: FragmentHostBinding
    private  val binding get() = viewBinding

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
    }
    private fun clickStartButton() {
        if (viewBinding.textboxName.text.toString().isEmpty()) {
            Toast.makeText(requireActivity(), "Bitte gib einen namen ein", Toast.LENGTH_SHORT).show()
        } else {
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