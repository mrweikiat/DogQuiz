package com.example.dogquiz.ui.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dogquiz.DogQuizState

@Composable
fun DogQuizFailureView(
    onRetryClicked: () -> Unit,
    state: DogQuizState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Incorrect!")

        Spacer(modifier = Modifier.height(8.dp))

        if (state.currentQuestion != null) {
            val correctAnswer =
                state.currentQuestion.correctAnswerIndex?.let { state.currentQuestion.options[it] }
            Text(
                text = "The correct answer was:\n$correctAnswer",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = onRetryClicked,
            modifier = Modifier.width(150.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Start over", fontSize = 14.sp)
        }
    }
}