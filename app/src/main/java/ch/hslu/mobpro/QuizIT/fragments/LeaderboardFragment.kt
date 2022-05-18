package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ch.hslu.mobpro.QuizIT.Leaderboard
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
            var minutes: Long = ((Score.timeInMilliseconds/1000)/60)
            var second: Long = ((Score.timeInMilliseconds/1000)%60)
            var payload: String = minutes.toString() + "min " + second.toString() +"sec"
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
        quizViewModel.leaderboard.observe(this) { apileaderboard ->
            scoresAsList = apileaderboard
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
        val itemAdapter = ItemAdapter(getUsernameList(),getScoreList(), getTimeList())
        viewBinding.recyclerViewItems.adapter = itemAdapter
        viewBinding.recyclerViewItems.layoutManager = LinearLayoutManager(this.context)
    }

    private fun requestLeaderboard(){
        quizViewModel.getLeaderBoard()
    }
}