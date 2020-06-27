package com.fattmerchant.invoiceapplication.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fattmerchant.invoiceapplication.MainActivity
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.MediaData


class EpisodeListAdapter(private val episodesList: List<MediaData>, val context: Context) :
    RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    private var screenWidth = 0
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.newepisode_list_item_layout, p0, false)
        val displayMetrics = DisplayMetrics()
        (context as MainActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        if(episodesList.size>6)
            return 6
        else{
            return  episodesList.size
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvheader?.text = episodesList[position].title
        viewHolder.tvChannels?.text = episodesList[position].channel.toString()
        val itemWidth = screenWidth / 2.13

        val lp = viewHolder.llCard.layoutParams
       // lp.width = itemWidth.toInt()
      //  viewHolder.itemView.llCard.layoutParams = lp

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

        Glide.with(context).
        load(episodesList[position].coverAsset?.url).
        diskCacheStrategy(DiskCacheStrategy.ALL).
        apply(requestOptions).
        into(viewHolder.image)


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvheader = itemView.findViewById<TextView>(R.id.tvheader)
        val tvChannels = itemView.findViewById<TextView>(R.id.tvChannels)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val llCard = itemView.findViewById<LinearLayout>(R.id.llCard)

    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int,type:String)
    }
}