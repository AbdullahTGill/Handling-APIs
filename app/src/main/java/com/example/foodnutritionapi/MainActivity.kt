package com.example.foodnutritionapi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.collections.forEach

import com.example.foodnutritionapi.data.NutritionRepoImpl
import com.example.foodnutritionapi.data.Result
import com.example.foodnutritionapi.data.model.Nutrition
import com.example.foodnutritionapi.presentation.NutritionViewModel
import com.example.foodnutritionapi.ui.theme.FoodNutritionAPITheme

class MainActivity : ComponentActivity() {
    //create the viewmodel factory to pass the api since we are not using dependency injection
    private val viewModel by viewModels<NutritionViewModel> (factoryProducer = {
        object : ViewModelProvider.Factory {
            override  fun <T: ViewModel> create(modelClass: Class<T>): T {
                return NutritionViewModel(NutritionRepoImpl(RetrofitInstance.api))
                    as T

            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodNutritionAPITheme {

                Surface(
                    modifier =Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val nutritionList = viewModel.nutrition.collectAsState().value
//                    val context = LocalContext.current

                    NutritionScreen(viewModel)



                }

            }
        }
    }
}

@Composable
fun NutritionScreen(viewModel: NutritionViewModel) {
    val nutritionState = viewModel.nutrition.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        when (nutritionState) {

            is Result.Success -> {
                val nutrition = nutritionState.data
                if (nutrition != null) {
                    Text(text = "Calories: ${nutrition.calories}")
                    Text(text = "Cautions: ${nutrition.cautions.joinToString(", ")}")
                    Text(text = "Diet Labels: ${nutrition.dietLabels.joinToString(", ")}")
                    Text(text = "Health Labels: ${nutrition.healthLabels.joinToString(", ")}")
                    Text(text = "Total Weight: ${nutrition.totalWeight}")
                    Text(text = "Yield: ${nutrition.yield}")
                    Text(text = "URI: ${nutrition.uri}")
                } else {
                    Text(text = "No nutrition data available")
                }
            }
            is Result.Failure -> {
                Text(text = "Error: ${nutritionState.message}")
            }
        }
    }
}
