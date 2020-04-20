package com.gabrielbmoro.programmingchallenge.presentation

sealed class ViewModelResult {
    object Loading : ViewModelResult()
    object Success : ViewModelResult()
    class Error(exception: Exception) : ViewModelResult()
}