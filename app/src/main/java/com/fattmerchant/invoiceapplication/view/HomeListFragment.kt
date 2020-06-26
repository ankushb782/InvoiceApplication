package com.fattmerchant.invoiceapplication.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fattmerchant.invoiceapp.CommonViewModel
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.HomeListAdapter
import com.fattmerchant.invoiceapplication.database.AppDatabase
import com.fattmerchant.invoiceapplication.model.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

class HomeListFragment : Fragment() {

    private val homeListModel: CommonViewModel by viewModel()

    var homeList =  mutableListOf<ChannelData>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val swipeRefresh = view?.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        recyclerView!!.addItemDecoration(DividerItemDecoration(requireActivity(),VERTICAL))
        var homeListAdapter: HomeListAdapter = HomeListAdapter(homeList,requireActivity())
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

                if (episodeList.size > 0){
                    Executors.newSingleThreadExecutor().execute {
                        AppDatabase.getDatabase(requireContext())?.postDao()?.deleteAll()
                        AppDatabase.getDatabase(requireContext())?.postDao()?.insertAll(episodeList.get(0))
                    }
                }

            }
        }))
        homeListModel.listOfChannels.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(channelsList:List<ChannelData>?) {
            channelsList?.let {
                homeList.addAll(channelsList)
                if (channelsList.size > 0){
                    Executors.newSingleThreadExecutor().execute {

                        for(i in 0 until channelsList.size){
                            AppDatabase.getDatabase(requireContext())?.postDao()?.insertAll(channelsList.get(i))
                        }

                    }
                }

            }
        }))
        homeListModel.listOfCategory.observe(requireActivity(), Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(categoryList:List<ChannelData>?) {
            categoryList?.let {
                swipeRefresh?.isRefreshing=false
                homeList.addAll(categoryList)
                if (categoryList.size > 0){
                    Executors.newSingleThreadExecutor().execute {
                        for(i in 0 until categoryList.size){
                           AppDatabase.getDatabase(requireContext())?.postDao()?.insertAll(categoryList.get(i))
                        }
                    }
                }
                homeListAdapter.notifyDataSetChanged()


            }
        }))


        Executors.newSingleThreadExecutor().execute {
            homeList.addAll(AppDatabase.getDatabase(requireContext())?.postDao()?.all()!!)
           requireActivity().runOnUiThread {
               if(homeList.size>0) {
                   homeListAdapter.notifyDataSetChanged()
               }else{
                   homeListModel.getEpisodes()
               }
           }


        }

   }

}



