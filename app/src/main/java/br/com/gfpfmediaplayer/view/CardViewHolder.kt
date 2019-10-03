package br.com.gfpfmediaplayer.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.media_item_card.view.*

class CardViewHolder(
    itemView: View,
    private val mRecyclerViewClickListener: MediaListRecyclerViewAdapter.RecyclerViewClickListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var mediaItemImage: ImageView = itemView.media_item_image
    var mediaItemName: TextView = itemView.media_item_name

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        mRecyclerViewClickListener.recyclerViewListClicked(view, layoutPosition)
    }
}