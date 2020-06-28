package com.fattmerchant.invoiceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.ChannelData

class HeaderChannelLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    var tvTitle: TextView? = null
    var tvCount: TextView? = null
    var image: ImageView? = null

    val obj = this

    init {

        var inflater: LayoutInflater = getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var itemView = inflater.inflate(R.layout.item_channel_header_layout, obj, true)
        tvTitle = itemView.findViewById(R.id.tvTitle)
        tvCount = itemView.findViewById(R.id.tvCount)
        image = itemView.findViewById(R.id.image)


    }

    fun getView(commonModel: ChannelData) {

        tvTitle?.text = commonModel.title
        tvCount?.text = commonModel.mediaCount.toString() + " episodes"


        if (commonModel.iconAsset != null && commonModel.iconAsset.thumbnailUrl != null) {
            Glide.with(context)
                .load(commonModel.iconAsset.thumbnailUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(image!!)
        }
    }

}