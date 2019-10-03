package br.com.gfpfmediaplayer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gfpfmediaplayer.R
import br.com.gfpfmediaplayer.service.domain.MediaItem
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MediaListRecyclerViewAdapter(
    private val mRecyclerViewClickListener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<CardViewHolder>() {

    private var mItems: MutableList<MediaItem>? = ArrayList()

    interface RecyclerViewClickListener {
        fun recyclerViewListClicked(v: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.media_item_card, parent, false)
        return CardViewHolder(layoutView, mRecyclerViewClickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        if (mItems != null && position < mItems!!.size) {
            val item = mItems!![position]

            //User image
            Picasso.get()
                .load(item.mThumbnail)
                .placeholder(R.drawable.ic_thumbnail)
                //.resize(150, 150)
                //.centerCrop()
                .into(holder.mediaItemImage)

            //User name
            holder.mediaItemName?.text = (item.mName)

            //Git hub url
            //holder.htmlUrl?.text = item.mId.toString()
        }
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }

    fun getItem(position: Int): MediaItem {
        return mItems!![position]
    }

    fun replaceData(items: MutableList<MediaItem>) {
        setList(items)
        notifyDataSetChanged()
    }

    fun appendData(items: MutableList<MediaItem>) {
        mItems!!.addAll(items)
        notifyDataSetChanged()
    }

    private fun setList(items: MutableList<MediaItem>) {
        mItems = items
    }
}