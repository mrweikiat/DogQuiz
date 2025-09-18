package com.example.dogquiz.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.dogquiz.DogQuizState
import com.example.dogquiz.Question
import com.example.dogquiz.ui.theme.DogQuizTheme
import kotlinx.coroutines.delay

@Composable
fun DogQuizQuestionView(
    navController: NavController,
    state: DogQuizState,
    correctAnswerClicked: () -> Unit,
    wrongAnswerClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
    onError: () -> Unit,
) {

    var showDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        showDialog = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.currentQuestion != null) {
            AsyncImage(
                model = state.currentQuestion.imageUrl,
                contentDescription = "Dog Quiz Image",
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "What breed is this dog?",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            state.currentQuestion.options.forEachIndexed { index, option ->
                Button(
                    onClick = {
                        if (index == state.currentQuestion.correctAnswerIndex) {
                            correctAnswerClicked()
                        } else {
                            wrongAnswerClicked()
                        }
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(text = option)
                }
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
            LaunchedEffect(Unit) {
                delay(5000)
                onError()
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Exit Quiz?") },
            text = { Text("Are you sure you want to end the quiz?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        navController.popBackStack()
                        onCloseButtonClicked()
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DogQuizQuestionViewPreview() {
    DogQuizTheme {
        DogQuizQuestionView(
            navController = rememberNavController(),
            state = DogQuizState(
                currentQuestion = Question(
                    imageUrl = "https://images.dog.ceo/breeds/retriever-flatcoated/n02099267_5551.jpg",
                    options = listOf("dog1", "dog2", "d0g3", "dog4"),
                    correctAnswerIndex = 1,
                )
            ),
            correctAnswerClicked = {},
            wrongAnswerClicked = {},
            onCloseButtonClicked = {},
            onError = {},
        )
    }
}