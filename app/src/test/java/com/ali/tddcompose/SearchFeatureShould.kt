package com.ali.tddcompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

class SearchFeatureShould {


    @ExtendWith
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searcherVM: SearcherVM

    @MockK(relaxed = true)
    private lateinit var validator: SearchValidator
    @MockK(relaxed = true)
    private lateinit var searcherRepo: SearchRepo


    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        searcherVM = SearcherVM(validator,searcherRepo)
    }

    @Test
    fun validateSearchQuery() = runTest {

        val query = "123"
        searcherVM.performSearch(query)

        verify {
            validator.validate(query)
        }
    }

    @Test
    fun searchItemFromValidQuery() =runTest {

        val query = "12312"
        every { validator.validate(query) }.answers { true }
        searcherVM.performSearch(query = query)

        verify {
            searcherRepo.searchListedItem(query)
        }
    }


    @Test
    fun tellAboutInValidQuery() = runTest {

        val query = "12"
        every { validator.validate(query) }.answers { false }
        searcherVM.performSearch(query)
        assertEquals(UiState.InvalidQuery, searcherVM.uiStateFlow.value)
    }

    @Test
    fun showLoadingWhileGettingSearchItems() = runTest {

        val query = "12312"

        every { validator.validate(query) }.answers { true }
        searcherVM.performSearch(query)

        assertEquals(UiState.Loading, searcherVM.uiStateFlow.value)

    }

    @Test
    fun fetchListOfItems() =  runTest{
        val items = listOf<String>("1,2,2,3")
        val query = "123"
        every { validator.validate(query) }.answers { true }
        every { searcherRepo.searchListedItem(query) }.answers { items }
        searcherVM.performSearch(query = query)
        val expectedResult = UiState.Success(items)
       assertEquals(expectedResult, searcherVM.uiStateFlow.value)

    }
}