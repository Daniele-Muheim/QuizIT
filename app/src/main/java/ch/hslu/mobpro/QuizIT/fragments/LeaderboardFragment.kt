package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment(R.layout.fragment_leaderboard) {
    private lateinit var viewBinding: FragmentLeaderboardBinding
    private  val binding get() = viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() : LeaderboardFragment {
            return LeaderboardFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewBinding = FragmentLeaderboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}