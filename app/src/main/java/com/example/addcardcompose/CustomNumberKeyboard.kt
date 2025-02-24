package com.example.addcardcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.addcardcompose.ui.theme.SpaceGroteskFontFamily

@Composable
fun CustomNumberKeyboard(
    modifier : Modifier,
    onNumberClicked : (Char) -> Unit,
    onDeleteClicked : () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomNumberButton(value = 1, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 2, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 3, onClick = onNumberClicked, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomNumberButton(value = 4, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 5, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 6, onClick = onNumberClicked, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomNumberButton(value = 7, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 8, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomNumberButton(value = 9, onClick = onNumberClicked, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1F))
            CustomNumberButton(value = 0, onClick = onNumberClicked, modifier = Modifier.weight(1f))
            CustomDeleteButton(onClick = onDeleteClicked, modifier = Modifier.weight(1F))
        }
    }
}

@Composable
fun CustomNumberButton(
    value : Int,
    onClick : (Char) -> Unit,
    modifier : Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        onClick = { onClick(value.toString().first()) }
    ) {
        Text(
            text = value.toString(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = SpaceGroteskFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun CustomDeleteButton(
    onClick : () -> Unit,
    modifier : Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "delete",
            tint = Color.DarkGray
        )
    }
}