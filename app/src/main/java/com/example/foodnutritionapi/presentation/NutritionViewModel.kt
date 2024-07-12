package com.example.foodnutritionapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodnutritionapi.data.JokesRepo
import com.example.foodnutritionapi.data.Result
import com.example.foodnutritionapi.data.model.JokesAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JokesViewModel(private val repo: JokesRepo) : ViewModel() {
    private val _joke = MutableStateFlow<Result<JokesAPI>>(Result.Failure("No Data"))
    val joke: StateFlow<Result<JokesAPI>> = _joke

    init {
        fetchJoke()
    }

    private fun fetchJoke() {
        viewModelScope.launch {
            repo.postJoke().collect {
                _joke.value = it
            }
        }
    }
}
