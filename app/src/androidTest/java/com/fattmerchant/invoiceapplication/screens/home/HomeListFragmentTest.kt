package com.fattmerchant.invoiceapplication.screens.home

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fattmerchant.invoiceapplication.MainActivity
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.adapter.HomeListAdapter
import com.fattmerchant.invoiceapplication.base.BaseUITest
import com.fattmerchant.invoiceapplication.di.generateTestAppComponent
import com.fattmerchant.invoiceapplication.helpers.recyclerItemAtPosition
import com.fattmerchant.invoiceapplication.view.HomeListFragment
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class HomeListFragmentTest : BaseUITest() {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    //Inject Mockwebserver created with koin
    val  mMockWebServer : MockWebServer by inject()

    val mEpisodeNameTestOne = "Conscious Parenting"
    val mEpisodeNameTestTwo = "Recorded Live Calls: Intellectual Life"
    val mChannelTestOne = "Coaching Mastery by Evercoach"
    val mCategoryTestOne = "Career"
    val mCategoryTestTwo = "Spiritual"

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {

        mockNetworkResponseWithFileContent("success_resp_episodes.json", HttpURLConnection.HTTP_OK)
        SystemClock.sleep(1000)
        mockNetworkResponseWithFileContent("success_resp_channels.json", HttpURLConnection.HTTP_OK)
        SystemClock.sleep(1000)
        mockNetworkResponseWithFileContent("success_resp_categories.json", HttpURLConnection.HTTP_OK)

        mActivityTestRule.launchActivity(null)

        mActivityTestRule.activity.supportFragmentManager.findFragmentById(R.id.frag_container)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.main_recyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mEpisodeNameTestOne))
                    )))

        SystemClock.sleep(1000)

        //Scroll to 4th channels
        onView(withId(R.id.main_recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<HomeListAdapter.ViewHolder>(4))

        SystemClock.sleep(1000)

        onView(withId(R.id.main_recyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        4,
                        ViewMatchers.hasDescendant(withText(mChannelTestOne))
                    )))

        //Scroll to categories
        onView(withId(R.id.main_recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<HomeListAdapter.ViewHolder>(10))

        SystemClock.sleep(1000)

        //Check if item at 9th position is having 9th element in json
        onView(withId(R.id.main_recyclerView))
            .check(matches(recyclerItemAtPosition(10, ViewMatchers.hasDescendant(withText(mCategoryTestOne)))))

        onView(withId(R.id.main_recyclerView))
            .check(matches(recyclerItemAtPosition(10, ViewMatchers.hasDescendant(withText(mCategoryTestTwo)))))

    }
}