package ch.hslu.mobpro.QuizIT.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ch.hslu.mobpro.QuizIT.R
import ch.hslu.mobpro.QuizIT.adapters.ItemAdapter
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

    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 1..10) {
            list.add("Item $i")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = ItemAdapter(getItemsList())
        viewBinding.recyclerViewItems.adapter = itemAdapter
        viewBinding.recyclerViewItems.layoutManager = LinearLayoutManager(this.context)

        viewBinding.startNewQuizButton.setOnClickListener { clickNewQuizButton() }

    }

    private fun clickNewQuizButton() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.hostFragment, HostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}