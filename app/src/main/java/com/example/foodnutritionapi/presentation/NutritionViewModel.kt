package com.example.foodnutritionapi.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodnutritionapi.data.NutritionRepo
import com.example.foodnutritionapi.data.Result
import com.example.foodnutritionapi.data.model.Nutrition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//View model is used for
class NutritionViewModel(private val repo: NutritionRepo) : ViewModel() {
    //This line declares a private mutable state flow variable named _nutrition.
    private val _nutrition = MutableStateFlow<Result<Nutrition>>(Result.Failure("No Data"))// Result.Failure("No Data"), indicating that there's no data available initially.
    //MutableStateFlow is a state holder from Jetpack Compose Flow library that allows holding and updating a single value of type Result<Nutrition>.
    val nutrition: StateFlow<Result<Nutrition>> = _nutrition //This line exposes an immutable state flow named nutrition publicly. This allows other composables in your UI to access the current nutrition data (success or failure).
    // StateFlow is a read-only version of MutableStateFlow that ensures composables only observe the data and cannot modify it directly.

    //initializer for viewmodel
    init{
        fetchNutrition()
    }

// This private function handles fetching nutrition data.

    private fun fetchNutrition() {
        viewModelScope.launch {
            repo.postNutrition().collect {
                _nutrition.value = it
            }
        }
    }
}