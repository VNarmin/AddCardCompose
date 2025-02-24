package com.example.addcardcompose

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InterceptPlatformTextInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addcardcompose.ui.theme.SpaceGroteskFontFamily
import kotlinx.coroutines.awaitCancellation

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpiryDateTextField(
    modifier : Modifier,
    value : TextFieldValue,
    error : Boolean
) {
    CompositionLocalProvider(LocalSoftwareKeyboardController provides null) {
        InterceptPlatformTextInput(
            interceptor = { _, _ -> awaitCancellation() },
            content = {
                TextField(
                    modifier = modifier,
                    label = {
                        Text(
                            text = "Exp.Date",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = SpaceGroteskFontFamily,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "MM/YY",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = SpaceGroteskFontFamily,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Start
                            )
                        )
                    },
                    value = value,
                    onValueChange = { },
                    visualTransformation = ExpiryDateTransformation(),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = SpaceGroteskFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray,
                        errorTextColor = Color.Red,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.DarkGray,
                        errorIndicatorColor = Color.Red,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.DarkGray,
                        errorLabelColor = Color.Red
                    ),
                    singleLine = true,
                    isError = error,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.offset(x = 16.dp, y = 16.dp),
                            painter = painterResource(R.drawable.question_mark),
                            contentDescription = "question mark",
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        )
    }
}