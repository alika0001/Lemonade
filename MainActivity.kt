package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeScreen()
                }
            }
        }
    }
}

@Composable
fun LemonadeScreen() {
    var currentStep by remember { mutableStateOf(1) }
    var tapsNeeded by remember { mutableStateOf(0) }
    var tapsDone by remember { mutableStateOf(0) }

    val imageToShow = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textToShow = when (currentStep) {
        1 -> R.string.tap_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_drink
        else -> R.string.tap_restart
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textToShow),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        Image(
            painter = painterResource(imageToShow),
            contentDescription = stringResource(textToShow),
            modifier = Modifier
                .size(190.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = 2.dp,
                    color = Color(0xFF2E7D32),
                    shape = MaterialTheme.shapes.medium
                )
                .clickable {
                    when (currentStep) {
                        1 -> {
                            currentStep = 2
                            tapsNeeded = Random.nextInt(2, 5)
                            tapsDone = 0
                        }
                        2 -> {
                            tapsDone++
                            if (tapsDone >= tapsNeeded) {
                                currentStep = 3
                            }
                        }
                        3 -> currentStep = 4
                        4 -> currentStep = 1
                    }
                }
        )
    }
}
