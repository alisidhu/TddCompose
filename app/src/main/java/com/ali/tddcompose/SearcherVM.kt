package com.ali.tddcompose

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearcherVM(
    private val validator: SearchValidator,
    private val searcherRepo: SearchRepo
){

    private val _uiStateFlow = MutableStateFlow<UiState>(UiState.NONE)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    suspend fun performSearch(query: String) {
        _uiStateFlow.emit(UiState.Loading)
        if(validator.validate(query)){
            _uiStateFlow.emit(UiState.Success(searcherRepo.searchListedItem(query)))
        }
        else {
            _uiStateFlow.emit(UiState.InvalidQuery)
        }
    }

}
