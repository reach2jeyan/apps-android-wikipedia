package org.wikipedia.search

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.Constants
import org.wikipedia.R
import org.wikipedia.TestUtil

@LargeTest
@RunWith(AndroidJUnit4::class)
class clearRecentSearch {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule<SearchActivity>(
            Intent(ApplicationProvider.getApplicationContext(), SearchActivity::class.java)
                    .setAction(Intent.ACTION_SEND)
                    .setType(Constants.PLAIN_TEXT_MIME_TYPE)
                    .putExtra(Intent.EXTRA_TEXT, "Daredevil")
                    .setType(Constants.ACTIVITY_REQUEST_OPEN_SEARCH_ACTIVITY.toString())


    )
    @Test
    fun clearSearch() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        onView(allOf(withId(R.id.search_toolbar), isDisplayed()))
                .perform(ViewActions.typeText("Daredevil"))
        TestUtil.delay(5)
        onView(allOf(withId(R.id.list_title), isDisplayed()))
                .perform(ViewActions.click())
        TestUtil.delay(5)
        onView(allOf(withId(R.id.page_toolbar_button_search), isDisplayed()))
                .perform(ViewActions.click())
        TestUtil.delay(5)
        onView(allOf(withText("Recent Searches"), isDisplayed()))
        TestUtil.delay(5)
        onView(allOf(withId(R.id.recent_searches_delete_button), isDisplayed()))
                .perform(ViewActions.click())
        TestUtil.delay(5)
        onView(allOf(withText("YES"), isDisplayed()))
                .perform(ViewActions.click())
        TestUtil.delay(5)
        onView(allOf(withId(R.id.recent_searches_delete_button), not(isDisplayed())))






    }
}

