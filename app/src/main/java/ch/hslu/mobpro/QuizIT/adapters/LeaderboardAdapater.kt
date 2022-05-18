package ch.hslu.mobpro.QuizIT.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.hslu.mobpro.QuizIT.databinding.LeaderboardItemsBinding

class ItemAdapter(private val usernames: ArrayList<String>, private val scores: ArrayList<String>, private val times: ArrayList<String> ): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val viewBinding: LeaderboardItemsBinding) :RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LeaderboardItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val username = usernames[position]
        val score = scores[position]
        val time = times[position]
        with(holder) {
            if(username.length > 12){
                viewBinding.username.text = username.subSequence(0,12)
            } else {
                viewBinding.username.text = username
            }
            viewBinding.score.text = "$score/5"
            viewBinding.time.text = time
        }
    }

    override fun getItemCount(): Int {
       return usernames.size
    }
}

