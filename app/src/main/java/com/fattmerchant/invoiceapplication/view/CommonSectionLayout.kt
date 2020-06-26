package com.fattmerchant.invoiceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.ChannelData
import com.fattmerchant.invoiceapplication.model.CommonModel

class CommonSectionLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {


    var llLinearLayout: LinearLayout? = null
    var llEpisodes: NewEpisodeSectionLayout? = null
    var llChannels: ChannelsSectionLayout? = null
    var llCategories: CategoriesSectionLayout? = null

    val obj = this

    init {

        var inflater: LayoutInflater = getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var itemView = inflater.inflate(R.layout.item_common_layout, obj, true)

        llLinearLayout = itemView.findViewById(R.id.llLinearLayout)
        llEpisodes = itemView.findViewById(R.id.llEpisodes)
        llChannels = itemView.findViewById(R.id.llChannels)
        llCategories = itemView.findViewById(R.id.llCategories)


    }

    fun getView(commonModel: ChannelData, context: Context) {

        if (commonModel.data != null) {
            llEpisodes?.visibility = View.VISIBLE
            llChannels?.visibility = View.GONE
            llCategories?.visibility = View.GONE
            llEpisodes?.getView(commonModel, context)
        } else if (commonModel.categories != null && commonModel.categories.size > 0) {
            llEpisodes?.visibility = View.GONE
            llChannels?.visibility = View.GONE
            llCategories?.visibility = View.VISIBLE
            llCategories?.getView(commonModel, context)
        } else if (commonModel.mediaCount!! > 0) {
            llEpisodes?.visibility = View.GONE
            llChannels?.visibility = View.VISIBLE
            llCategories?.visibility = View.GONE
            llChannels?.getView(commonModel, context)
        }


    }


}