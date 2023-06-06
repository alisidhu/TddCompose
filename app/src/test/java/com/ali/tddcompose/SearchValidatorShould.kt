package com.ali.tddcompose

import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchValidatorShould{


    private lateinit var searchValidator: SearchValidator

    @BeforeEach
    fun before() {
        searchValidator =  SearchValidator()
    }

    @Test
    fun invalidateEmptyQuery() {
        val query = ""
        assertEquals(false, searchValidator.validate(query = query) )

    }

    @Test
    fun invalidateQueryWhenQueryIsLessThan3() {
        assertEquals(false, searchValidator.validate("12"))
    }

    @Test
    fun validateQuery() {
        assertEquals(true, searchValidator.validate("123"))
    }
}