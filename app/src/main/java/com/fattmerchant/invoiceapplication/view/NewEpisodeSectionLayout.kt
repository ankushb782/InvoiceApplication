package com.fattmerchant.invoiceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.EpisodeListAdapter
import com.fattmerchant.invoiceapplication.model.ChannelData


class NewEpisodeSectionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    var recyclerView: RecyclerView? = null

    val obj = this

    init {
        var inflater: LayoutInflater = getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var itemView = inflater.inflate(R.layout.item_newepisode_layout, obj, true)
        recyclerView = itemView.findViewById(R.id.recyclerView)


    }

    fun getView(commonModel: ChannelData, context: Context) {


        recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        if (commonModel.data != null) {
            var episodesListAdapter: EpisodeListAdapter = EpisodeListAdapter(commonModel.data.media, context)
            recyclerView?.adapter = episodesListAdapter
        }


    }

}