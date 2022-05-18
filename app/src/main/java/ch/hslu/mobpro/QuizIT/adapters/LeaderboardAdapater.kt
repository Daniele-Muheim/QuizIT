package ch.hslu.mobpro.QuizIT.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.hslu.mobpro.QuizIT.databinding.LeaderboardItemsBinding

class ItemAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

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
        val item = items[position]
        with(holder) {
            viewBinding.username.text = item
            viewBinding.score.text = items.size.toString()
            viewBinding.time.text = "2min 30sek"
        }
    }


    override fun getItemCount(): Int {
       return items.size
    }

}

