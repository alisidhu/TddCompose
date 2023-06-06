package com.ali.tddcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test


class  MainActivityTest{
    @get:Rule
    val composeTestRule =  createComposeRule()

    @Test
    fun myComposeComponent() {
        val name = "John"
        composeTestRule.setContent {
            Greeting(name = name)
        }

        composeTestRule.onNodeWithText("Hello $name!").assertIsDisplayed()

    }


}