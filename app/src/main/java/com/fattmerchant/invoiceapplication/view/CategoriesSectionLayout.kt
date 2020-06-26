package com.fattmerchant.invoiceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapplication.GridSpacingItemDecoration
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.CategoryListAdapter
import com.fattmerchant.invoiceapplication.model.ChannelData


class CategoriesSectionLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null):
        LinearLayout(context,attrs) {

    var recyclerView: RecyclerView?=null
    val obj=this
    init {
        var inflater: LayoutInflater = getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var itemView = inflater.inflate(R.layout.item_categories_layout, obj, true)
        recyclerView = itemView.findViewById(R.id.recyclerView)



    }

     fun getView(commonModel: ChannelData,context: Context)  {

         recyclerView!!.layoutManager = GridLayoutManager(context, 2)
         val spanCount = 2 // 2 columns

         val spacing = 15 // 15px

         val includeEdge = true
         recyclerView!!.addItemDecoration(
             GridSpacingItemDecoration(
                 spanCount,
                 spacing,
                 includeEdge
             )
         )
         if(commonModel.categories!=null) {
             var categoryListAdapter: CategoryListAdapter = CategoryListAdapter(commonModel.categories)
             recyclerView?.adapter = categoryListAdapter
         }

    }

}