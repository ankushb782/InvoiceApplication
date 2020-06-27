package com.fattmerchant.invoiceapplication.repository

import com.fattmerchant.invoiceapplication.DataRepository
import com.fattmerchant.invoiceapplication.NetWorkApi
import com.fattmerchant.invoiceapplication.base.BaseUTTest
import com.fattmerchant.invoiceapplication.di.configureTestAppComponent
import com.fattmerchant.invoiceapplication.model.CategoryModel
import com.fattmerchant.invoiceapplication.model.ChannelData
import com.fattmerchant.invoiceapplication.model.ChannelsModel
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import java.net.HttpURLConnection
import okhttp3.mockwebserver.MockWebServer
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DataRepositoryTest : BaseUTTest() {

    //Target
    private lateinit var mRepo: DataRepository
    //Inject api service created with koin
    val mAPIService : NetWorkApi by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @Before
    fun start(){
        super.setUp()

        startKoin(
            configureTestAppComponent(getMockWebServerUrl())
        )
    }

    @Test
    fun test_data_repo_retrieves_episodes_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_resp_episodes.json", HttpURLConnection.HTTP_OK)
        mRepo = DataRepository()

        val channelData = getEpisodesSync(mRepo)

        assertNotNull(channelData)
        assertNotNull(channelData!!.data)
        assertTrue("Media is empty in episodes", channelData.data!!.media.isNotEmpty())
    }

    @Test
    fun test_data_repo_retrieves_channels_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_resp_channels.json", HttpURLConnection.HTTP_OK)
        mRepo = DataRepository()

        val channelMedia = getChannelsSync(mRepo)

        assertNotNull(channelMedia)
        assertNotNull(channelMedia!!.data)
        assertTrue("Channels is empty in channels api", channelMedia.data!!.channels.isNotEmpty())
    }

    @Test
    fun test_data_repo_retrieves_categories_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_resp_categories.json", HttpURLConnection.HTTP_OK)
        mRepo = DataRepository()

        val categoryModel = getCategoriesSync(mRepo)

        assertNotNull(categoryModel)
        assertNotNull(categoryModel!!.data)
        assertTrue("Categories is empty in categories api", categoryModel.data!!.categories.isNotEmpty())
    }

    suspend fun getEpisodesSync(mRepo: DataRepository): ChannelData?
            = suspendCoroutine { cont ->
        mRepo.getEpisodes(object : DataRepository.OnResponseData {
            override fun onSuccess(data: ChannelData) {
                cont.resume(data)
            }

            override fun onFailure() {
                cont.resume(null)
            }
        })
    }

    suspend fun getChannelsSync(mRepo: DataRepository): ChannelsModel?
            = suspendCoroutine { cont ->
        mRepo.getChannels(object : DataRepository.OnResponseDataChannel {
            override fun onSuccess(data: ChannelsModel) {
                cont.resume(data)
            }

            override fun onFailure() {
                cont.resume(null)
            }
        })
    }

    suspend fun getCategoriesSync(mRepo: DataRepository): CategoryModel?
            = suspendCoroutine { cont ->
        mRepo.getCategory(object : DataRepository.OnResponseDataCategory {
            override fun onSuccess(data: CategoryModel) {
                cont.resume(data)
            }

            override fun onFailure() {
                cont.resume(null)
            }
        })
    }
}