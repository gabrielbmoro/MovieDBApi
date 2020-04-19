package com.gabrielbmoro.programmingchallenge.presentation

sealed class ViewModelResult {
    object Loading : ViewModelResult()
    class Success<in T>(data: T) : ViewModelResult()
    class Error(exception: Exception): ViewModelResult()
}