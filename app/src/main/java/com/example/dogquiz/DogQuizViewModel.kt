package com.example.dogquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogquiz.data.DogQuizRepository
import com.example.dogquiz.utilities.parsing.DogQuizParserUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogQuizViewModel : ViewModel() {
    private val repository = DogQuizRepository()
    private val _quizState = MutableStateFlow(DogQuizState())
    val quizState: StateFlow<DogQuizState> = _quizState

    fun fetchDogImages() {
        viewModelScope.launch {
            try {
                val imageUrls = repository.getDogImages()

                if (imageUrls.size == 4) {
                    val correctAnswerUrl = imageUrls.random()
                    val correctAnswerBreed = DogQuizParserUtils.getBreedFromUrl(correctAnswerUrl)
                    val options = imageUrls.map { DogQuizParserUtils.getBreedFromUrl(it) }
                        .shuffled()

                    val correctAnswerIndex = options.indexOf(correctAnswerBreed)

                    val question = Question(
                        imageUrl = correctAnswerUrl,
                        options = options,
                        correctAnswerIndex = correctAnswerIndex
                    )

                    _quizState.value = DogQuizState(currentQuestion = question)
                } else {
                    Log.e("DogQuizViewModel", "Expected 4 dog images, but received ${imageUrls.size}")
                }
            } catch (e: Exception) {
                Log.e("DogQuizViewModel", "Error fetching dog images: ${e.message}", e)
            }
        }
    }

    fun clearState() {
        _quizState.value = DogQuizState()
    }
}