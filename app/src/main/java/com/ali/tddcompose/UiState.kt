package com.ali.tddcompose

sealed class UiState {
    data class Success(val data: List<Any>) : UiState()
    object Error : UiState()
    object Loading : UiState()
    object InvalidQuery : UiState()
    object NONE : UiState()
}
