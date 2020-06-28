package com.fattmerchant.invoiceapplication.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fattmerchant.invoiceapplication.MainActivity
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.LatestMedia
import kotlinx.android.synthetic.main.channels_list_item_layout.view.*


class ChannelsListAdapter(private val channelsList: List<LatestMedia>, val context: Context) :
    RecyclerView.Adapter<ChannelsListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    companion object{
        const val NORMAL_IMAGE          = 0
        const val BIG_IMAGE         = 1

        const val NormalImageLayout     = R.layout.channels_list_item_layout
        const val BigImageLayout    = R.layout.channels_list_item_bigimage_layout
    }

    override fun getItemViewType(position: Int): Int  {
        if(channelsList[position].type!=null){
            return NORMAL_IMAGE
        }else{
            return BIG_IMAGE
        }

    }

    private var screenWidth = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val displayMetrics = DisplayMetrics()
        (context as MainActivity).windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        when (viewType) {

            NORMAL_IMAGE -> {
                val view = inflateLayoutView(NormalImageLayout, parent)
                return ViewHolder(view)
            }

            BIG_IMAGE -> {
                val view = inflateLayoutView(BigImageLayout, parent)
                return ViewHolder(view)
            }



            else -> {
                val view = inflateLayoutView(NormalImageLayout, parent)
                return ViewHolder(view)
            }
        }

    }
    fun inflateLayoutView(viewResourceId: Int, parent: ViewGroup?, attachToRoot: Boolean = false): View =
        LayoutInflater.from(parent?.context).inflate(viewResourceId, parent, attachToRoot)


    override fun getItemCount(): Int {
        if(channelsList.size>6)
        return 6
        else{
            return   channelsList.size
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvheader?.text = channelsList[position].title
        var itemWidth=0.0
        if(channelsList[position].type==null){
             itemWidth = screenWidth / 1.13

        }else{
             itemWidth = screenWidth / 2.18
        }

        val lp = viewHolder.llCard.layoutParams
        lp.width = itemWidth.toInt()
        viewHolder.itemView.llCard.layoutParams = lp

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

        if(channelsList[position].coverAsset.url!=null){
            Glide.with(context).
            load(channelsList[position].coverAsset.url).
            diskCacheStrategy(DiskCacheStrategy.ALL).
            apply(requestOptions).
            into(viewHolder.image)
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvheader = itemView.findViewById<TextView>(R.id.tvheader)
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