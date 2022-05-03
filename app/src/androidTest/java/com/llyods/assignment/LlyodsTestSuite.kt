package com.llyods.assignment

import com.llyods.assignment.presentation.views.activities.MainActivityTest
import com.llyods.assignment.presentation.views.fragments.TopArtistFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    TopArtistFragmentTest::class
)
class LlyodsTestSuite