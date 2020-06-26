package com.fattmerchant.invoiceapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.ChannelData
import com.fattmerchant.invoiceapplication.view.CommonSectionLayout


class HomeListAdapter(private val homeList: List<ChannelData>, private val context:Context) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.home_list_item_main, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.llLinearLayout.getView(homeList.get(position),context)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llLinearLayout: CommonSectionLayout = itemView.findViewById<CommonSectionLayout>(R.id.llLinearLayout)

    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int,type:String)
    }
}