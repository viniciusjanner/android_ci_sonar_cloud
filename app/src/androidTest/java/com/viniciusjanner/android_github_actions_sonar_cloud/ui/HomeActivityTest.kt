package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.viniciusjanner.android_github_actions_sonar_cloud.R
import com.viniciusjanner.android_github_actions_sonar_cloud.util.EspressoMatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {}

    @Test
    fun shouldDisplayToolbar_WithCorrectText(): Unit = runBlocking {
        //
        // deve exibir a toolbar com o texto correto
        //
        val viewId = R.id.toolbar
        val viewText = R.string.app_name
        onView(withId(viewId)).check(matches(isDisplayed()))
        onView(withText(viewText)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplaySwitch_And_WhenClicked_ShouldChangeStateAndText(): Unit = runBlocking {
        //
        // deve exibir o switch e quando clicado deve alterar o estado e o texto
        //
        val viewId = R.id.switchTheme
        val viewTextLight = R.string.home_mode_light
        val viewTextDark = R.string.home_mode_dark

        // Verificar o estado inicial do switch (desligado e com texto correto)
        onView(withId(viewId)).check(matches(isDisplayed()))
        onView(withId(viewId)).check(matches(withText(viewTextLight)))
        onView(withId(viewId)).check(matches(isNotChecked()))

        // Clicar no switch para alternar o tema
        onView(withId(viewId)).perform(click())

        // Verificar se o texto do switch e o estado foram atualizados após o clique
        onView(withId(viewId)).check(matches(withText(viewTextDark)))
        onView(withId(viewId)).check(matches(isChecked()))
    }

    @Test
    fun shouldDisplayImageView_WithCorrectIcon(): Unit = runBlocking {
        //
        // deve exibir imageView com o icon correto
        //
        val viewId = R.id.imageView
        val viewDrawable = R.drawable.ic_github_logo
        onView(withId(viewId)).check(matches(EspressoMatchers.hasDrawable()))
        onView(withId(viewId)).check(matches(EspressoMatchers.withDrawable(viewDrawable)))
    }

    @Test
    fun shouldDisplayImageView_WithoutIcon(): Unit = runBlocking {
        //
        // deve exibir imageViewEmpty sem o icone
        //
        val viewId = R.id.imageViewEmpty
        onView(withId(viewId)).check(matches(EspressoMatchers.noDrawable()))
    }

    @Test
    fun shouldDisplayTitle_WithCorrectText(): Unit = runBlocking {
        //
        // deve exibir title com o texto correto
        //
        val viewId = R.id.title
        val viewText = R.string.home_title
        onView(withId(viewId)).check(matches(isDisplayed()))
        onView(withText(viewText)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplaySubtitle_WithCorrectText(): Unit = runBlocking {
        //
        // deve exibir subtitle com o texto correto
        //
        val viewId = R.id.subtitle
        val viewText = R.string.home_subtitle
        onView(withId(viewId)).check(matches(isDisplayed()))
        onView(withText(viewText)).check(matches(isDisplayed()))
    }
}
