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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodnutritionapi.data.JokesRepoImpl
import com.example.foodnutritionapi.data.Result
import com.example.foodnutritionapi.presentation.JokesViewModel
import com.example.foodnutritionapi.ui.theme.FoodNutritionAPITheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<JokesViewModel> (factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return JokesViewModel(JokesRepoImpl(RetrofitInstance.api)) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodNutritionAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JokeScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun JokeScreen(viewModel: JokesViewModel) {
    val jokeState = viewModel.joke.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        when (jokeState) {
            is Result.Success -> {
                val joke = jokeState.data
                if (joke != null) {
                    Text(text = "Setup: ${joke.setup}")
                    Text(text = "Punchline: ${joke.punchline}")
                } else {
                    Text(text = "No joke data available")
                }
            }
            is Result.Failure -> {
                Text(text = "Error: ${jokeState.message}")
            }
        }
    }
}
