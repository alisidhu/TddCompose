package com.ali.tddcompose

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchRepoTestShould{


    @MockK(relaxed = true)
    private lateinit var searchRepo: SearchRepo

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun returnEmptyResponseWhenNoDataFound() {
        val result = emptyList<String>()
        val query = ""
        Assertions.assertEquals(result,searchRepo.searchListedItem(query) )
    }

    @Test
    fun returnResultResponseWhenNoDataFound() {
        val result = listOf("1,2,3,4")
        val query = "123"
        every { searchRepo.searchListedItem(query = query) }.answers { result }
        Assertions.assertEquals(result,searchRepo.searchListedItem(query) )
    }


    @Test
    fun handleNoNetworkError() {



    }

}