package com.example.dogquiz

data class Question(
    val imageUrl: String,
    val options: List<String>,
    val correctAnswerIndex: Int?,
)

data class DogQuizState(
    val currentQuestion: Question? = null,
)
