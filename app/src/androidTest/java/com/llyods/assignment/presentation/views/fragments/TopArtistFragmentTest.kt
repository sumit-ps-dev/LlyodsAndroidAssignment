package com.llyods.assignment.presentation.views.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.llyods.assignment.R
import com.llyods.assignment.presentation.views.activities.MainActivity
import com.llyods.assignment.presentation.views.adapter.TopArtistAdapter
import com.llyods.assignment.utility.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class TopArtistFragmentTest {

    val LIST_ITEM_INDEX = 10

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun deRegisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }


    @Test
    fun test_isFragmentLaunched() {
        onView(withId(R.id.fragment_top_artist)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerviewVisible() {
        onView(withId(R.id.rv_artists)).check(matches(isDisplayed()))
    }

    @Test
    fun test_itemClickedOnRecyclerview() {
        onView(withId(R.id.rv_artists))
            .perform(actionOnItemAtPosition<TopArtistAdapter.ArtistViewHolder>(LIST_ITEM_INDEX,
                ViewActions.click()))

        onView(withId(R.id.fragment_artist_detail))
            .check(matches(isDisplayed()))
    }
}