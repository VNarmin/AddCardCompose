package com.example.addcardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomNumberKeyboard(
        modifier = Modifier.fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.36F),
        onNumberClicked = { },
        onDeleteClicked = { }
    )
}