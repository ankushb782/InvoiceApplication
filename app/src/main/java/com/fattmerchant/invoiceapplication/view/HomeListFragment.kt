package com.fattmerchant.invoiceapplication.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.fattmerchant.invoiceapp.CommonViewModel
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.HomeListAdapter
import com.fattmerchant.invoiceapplication.database.AppDatabase
import com.fattmerchant.invoiceapplication.model.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

class HomeListFragment : Fragment() {

    private val homeListModel: CommonViewModel by viewModel()

    private val database:AppDatabase by inject()

    var  mShimmerViewContainer:ShimmerFrameLayout?=null
    var homeList =  mutableListOf<ChannelData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val swipeRefresh = view?.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
          mShimmerViewContainer =view?.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container);
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        recyclerView!!.addItemDecoration(DividerItemDecoration(requireActivity(),VERTICAL))
        val homeListAdapter: HomeListAdapter = HomeListAdapter(homeList,requireActivity())
        recyclerView.adapter = homeListAdapter

        swipeRefresh?.setOnRefreshListener {

            homeListModel.getEpisodes()
        }

        homeListAdapter.setItemClickListener(object : HomeListAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int,type:String) {

            }
        })


        homeListModel.listOfEpisodes.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(episodeList:List<ChannelData>?) {
            episodeList?.let {
                homeList.clear()
                homeList.addAll(episodeList)
            }
        }))

        homeListModel.listOfChannels.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(channelsList:List<ChannelData>?) {
            channelsList?.let {
                homeList.addAll(channelsList)

            }
        }))
        homeListModel.listOfCategory.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(categoryList:List<ChannelData>?) {
            categoryList?.let {

                mShimmerViewContainer?.visibility=(View.GONE)
                mShimmerViewContainer?.stopShimmerAnimation()
                swipeRefresh?.isRefreshing=false
                homeList.addAll(categoryList)


                recyclerView?.visibility=View.VISIBLE
                homeListAdapter.notifyDataSetChanged()
            }
        }))

        homeListModel.errorData.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(message:String?) {
            message?.let {
                swipeRefresh?.isRefreshing=false
                mShimmerViewContainer?.visibility=(View.GONE)
                mShimmerViewContainer?.stopShimmerAnimation()
           Toast.makeText(requireActivity(),message,Toast.LENGTH_SHORT).show()

            }
        }))

        //homeListModel.getEpisodes()

        Executors.newSingleThreadExecutor().execute {
            homeList.addAll(database.postDao().all())
           requireActivity().runOnUiThread {
               if(homeList.size>0) {
                  mShimmerViewContainer?.stopShimmerAnimation()
                   mShimmerViewContainer?.setVisibility(View.GONE)
                   recyclerView.visibility=View.VISIBLE
                   homeListAdapter.notifyDataSetChanged()
               }else{
                   homeListModel.getEpisodes()
               }
           }
        }


   }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer?.visibility=(View.VISIBLE)
        mShimmerViewContainer?.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        mShimmerViewContainer?.stopShimmerAnimation()
    }

}



