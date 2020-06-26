package com.fattmerchant.invoiceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.ChannelsListAdapter
import com.fattmerchant.invoiceapplication.model.ChannelData


class ChannelsSectionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    var recyclerView: RecyclerView? = null
    var header: HeaderChannelLayout? = null
    val obj = this

    init {
        var inflater: LayoutInflater = getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var itemView = inflater.inflate(R.layout.item_channels_layout, obj, true)
        recyclerView = itemView.findViewById(R.id.recyclerView)
        header = itemView.findViewById(R.id.header)


    }

    fun getView(commonModel: ChannelData, context: Context) {

        header?.getView(commonModel)

        recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        var channelsListAdapter: ChannelsListAdapter = ChannelsListAdapter(commonModel.latestMedia!!, context)
        if (commonModel.series!!.size > 0) {
            channelsListAdapter = ChannelsListAdapter(commonModel.series!!, context)
        }
        recyclerView?.adapter = channelsListAdapter
    }

}