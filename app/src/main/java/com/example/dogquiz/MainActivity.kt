package com.example.dogquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogquiz.ui.quiz.DogQuizErrorView
import com.example.dogquiz.ui.quiz.DogQuizFailureView
import com.example.dogquiz.ui.theme.DogQuizTheme
import com.example.dogquiz.ui.quiz.DogQuizHomeView
import com.example.dogquiz.ui.quiz.DogQuizQuestionView
import com.example.dogquiz.ui.quiz.DogQuizSuccessView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogQuizTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val dogQuizViewModel: DogQuizViewModel = viewModel()

                    val quizState by dogQuizViewModel.quizState.collectAsStateWithLifecycle()

                    NavHost(navController = navController, startDestination = "dog_view") {
                        composable("dog_view") {
                            DogQuizHomeView(
                                onStartClicked = {
                                    navController.navigate("question_view")
                                    dogQuizViewModel.fetchDogImages()
                                }
                            )
                        }
                        composable("question_view") {
                            DogQuizQuestionView(
                                state = quizState,
                                correctAnswerClicked = { navController.navigate("success_view") },
                                wrongAnswerClicked = { navController.navigate("failure_view") },
                                onCloseButtonClicked = { dogQuizViewModel.clearState() },
                                onError = { navController.navigate("error_view") },
                                navController = navController,
                            )
                        }

                        composable("success_view") {
                            DogQuizSuccessView(
                                onRetryClicked = {
                                    navController.navigate("dog_view")
                                    dogQuizViewModel.clearState()
                                },
                            )
                        }

                        composable("failure_view") {
                            DogQuizFailureView(
                                onRetryClicked = {
                                    navController.navigate("dog_view")
                                    dogQuizViewModel.clearState()
                                },
                                state = quizState,
                            )
                        }

                        composable("error_view") {
                            DogQuizErrorView(
                                onRetryClicked = { navController.navigate("dog_view") },
                            )
                        }
                    }
                }
            }
        }
    }
}