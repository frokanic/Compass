package com.projects.compass.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.projects.compass.presentation.screen.compass.CompassScreen
import com.projects.compass.presentation.ui.theme.CompassTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompassTheme {
                CompassScreen()
            }
        }
    }
}