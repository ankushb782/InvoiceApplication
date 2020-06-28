package com.fattmerchant.invoiceapplication.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fattmerchant.invoiceapp.CommonViewModel
import com.fattmerchant.invoiceapplication.DataRepository
import com.fattmerchant.invoiceapplication.base.BaseUTTest
import com.fattmerchant.invoiceapplication.di.configureTestAppComponent
import com.fattmerchant.invoiceapplication.model.CategoryModel
import com.fattmerchant.invoiceapplication.model.ChannelData
import com.fattmerchant.invoiceapplication.model.ChannelsModel
import com.google.gson.Gson
import io.mockk.coEvery
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext
import java.net.HttpURLConnection
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RunWith(JUnit4::class)
class CommonViewModelTest: BaseUTTest(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mHomeViewModel: CommonViewModel

    val mDispatcher = Dispatchers.Unconfined

    val mParam = "1"
    val mNextValue = ""

    @Before
    fun start(){
        super.setUp()

        //Start Koin with required dependencies
        StandAloneContext.startKoin(
            configureTestAppComponent(getMockWebServerUrl())
        )
    }

    @Test
    fun test_home_view_model_episodes_populates_expected_value() =  runBlocking<Unit>{

        mHomeViewModel = CommonViewModel()

        mockNetworkResponseWithFileContent("success_resp_episodes.json", HttpURLConnection.HTTP_OK)

        mHomeViewModel.listOfEpisodes.observeForever {  }

        mHomeViewModel.getEpisodes()

        Thread.sleep(1000)

        assert(mHomeViewModel.listOfEpisodes.value != null)

        assertTrue("Episodes is empty", mHomeViewModel.listOfEpisodes.value!!.isNotEmpty())
    }

    @Test
    fun test_home_view_model_channels_populates_expected_value() =  runBlocking<Unit>{

        mHomeViewModel = CommonViewModel()

        mockNetworkResponseWithFileContent("success_resp_channels.json", HttpURLConnection.HTTP_OK)

        mHomeViewModel.listOfChannels.observeForever {  }

        mHomeViewModel.getChannels()

        Thread.sleep(1000)

        assert(mHomeViewModel.listOfChannels.value != null)

        assertTrue("Channels is empty", mHomeViewModel.listOfChannels.value!!.isNotEmpty())
    }

    @Test
    fun test_home_view_model_categories_populates_expected_value() =  runBlocking<Unit>{

        mHomeViewModel = CommonViewModel()

        mockNetworkResponseWithFileContent("success_resp_categories.json", HttpURLConnection.HTTP_OK)

        mHomeViewModel.listOfCategory.observeForever {  }

        mHomeViewModel.getCategory()

        Thread.sleep(1000)

        assert(mHomeViewModel.listOfCategory.value != null)

        assertTrue("Categories is empty", mHomeViewModel.listOfCategory.value!!.isNotEmpty())
    }
}