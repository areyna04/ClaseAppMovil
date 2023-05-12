package com.example.testloginapp.ui.login


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.testloginapp.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class ComentarAlbumTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun comentarAlbumTest() {
        val materialButton = onView(
allOf(withId(R.id.BtnUsuario), withText("Usuario"),
childAtPosition(
allOf(withId(R.id.container),
childAtPosition(
withId(android.R.id.content),
0)),
2),
isDisplayed()))
        materialButton.perform(click())
        
        val recyclerView = onView(
allOf(withId(R.id.albumsRv),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
2)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
        val materialButton2 = onView(
allOf(withId(R.id.botonCometarios), withText("Comentarios"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        materialButton2.perform(click())
        
        val appCompatEditText = onView(
allOf(withId(R.id.comentario),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        appCompatEditText.perform(replaceText("hola"), closeSoftKeyboard())
        
        val appCompatEditText2 = onView(
allOf(withId(R.id.rating),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
3),
isDisplayed()))
        appCompatEditText2.perform(replaceText("1"), closeSoftKeyboard())
        
        val materialButton3 = onView(
allOf(withId(R.id.botonAgregarComentario), withText("Agregar Comentario"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
4),
isDisplayed()))
        materialButton3.perform(click())
        
        val materialButton4 = onView(
allOf(withId(R.id.botonCometarios), withText("Comentarios"),
childAtPosition(
childAtPosition(
withId(R.id.nav_host_fragment),
0),
2),
isDisplayed()))
        materialButton4.perform(click())
        
        val textView = onView(
allOf(withText("Comentarios"),
withParent(allOf(withId(R.id.my_toolbar),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView.check(matches(withText("Comentarios")))
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
