package com.example.foodnutritionapi.data.model

data class Nutrition(
    val calories: Int,
    val cautions: List<Any>,
    val dietLabels: List<Any>,
    val healthLabels: List<String>,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalWeight: Int,
    val uri: String,
    val yield: Int
)
