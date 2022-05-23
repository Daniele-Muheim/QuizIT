package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ch.hslu.mobpro.QuizIT.QuizViewModel
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.Score
import ch.hslu.mobpro.QuizIT.adapters.ItemAdapter
import ch.hslu.mobpro.QuizIT.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment(R.layout.fragment_leaderboard) {
    private lateinit var viewBinding: FragmentLeaderboardBinding
    private  val binding get() = viewBinding
    private val quizViewModel: QuizViewModel by activityViewModels()
    private var scoresAsList: List<Score>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLeaderboard()
    }

    companion object {
        @JvmStatic
        fun newInstance() : LeaderboardFragment {
            return LeaderboardFragment()
        }
    }

    private fun getUsernameList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (Score in scoresAsList!!) {
            list.add(Score.username)
        }
        return list
    }
    private fun getScoreList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (Score in scoresAsList!!) {
            list.add(Score.score.toString())
        }
        return list
    }

    private fun getTimeList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (Score in scoresAsList!!) {
            val minutes = ((Score.timeInMilliseconds/1000)/60)
            val second = ((Score.timeInMilliseconds/1000)%60)
            val payload = "$minutes min $second sec"
            list.add(payload)
        }
        return list
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewBinding = FragmentLeaderboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        quizViewModel.leaderboard.observe(this) { apiLeaderboard ->
            scoresAsList = apiLeaderboard
            buildLeaderboard()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.startNewQuizButton.setOnClickListener { clickNewQuizButton() }
    }

    private fun clickNewQuizButton() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.hostFragment, HostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun buildLeaderboard(){
        val itemAdapter = ItemAdapter(getUsernameList(), getScoreList(), getTimeList())
        viewBinding.recyclerViewItems.adapter = itemAdapter
        viewBinding.recyclerViewItems.layoutManager = LinearLayoutManager(this.context)
    }

    private fun requestLeaderboard(){
        quizViewModel.getLeaderBoard()
    }
}