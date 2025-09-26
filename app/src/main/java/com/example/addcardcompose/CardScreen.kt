package com.example.addcardcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addcardcompose.ui.theme.ButtonYellow
import com.example.addcardcompose.ui.theme.CardYellow
import com.example.addcardcompose.ui.theme.SpaceGroteskFontFamily

@Composable
fun CardScreen() {
    val localFocusManager = LocalFocusManager.current

    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var expiryDate by remember { mutableStateOf(TextFieldValue("")) }
    var cvvCode by remember { mutableStateOf(TextFieldValue("")) }

    var cardNumberError by remember { mutableStateOf(false) }
    var expiryDateError by remember { mutableStateOf(false) }
    var cvvCodeError by remember { mutableStateOf(false) }

    // for tracking the focused text field
    var focusedField by remember { mutableStateOf < TextFieldType? > (null) }

    fun onNumberClicked(value : Char) {
        when (focusedField) {
            TextFieldType.CARD_NUMBER -> if (cardNumber.text.length < 16) {
                val newText = cardNumber.text + value
                cardNumber = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                cardNumberError = !validateCardNumber(inputValue = newText)
            }
            TextFieldType.EXPIRY_DATE -> if (expiryDate.text.length < 5) {
                val newText = expiryDate.text + value
                expiryDate = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                expiryDateError = !validateExpiryDate(inputValue = newText, currentYear = 25)
            }
            TextFieldType.CVV -> if (cvvCode.text.length < 3) {
                val newText = cvvCode.text + value
                cvvCode = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                cvvCodeError = !validateCVVCode(inputValue = newText)
            }
            null -> { }
        }
    }

    fun onDeleteClicked() {
        when (focusedField) {
            TextFieldType.CARD_NUMBER -> if (cardNumber.text.isNotEmpty()) {
                val newText = cardNumber.text.dropLast(1)
                cardNumber = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                cardNumberError = !validateCardNumber(inputValue = newText)
            }
            TextFieldType.EXPIRY_DATE -> if (expiryDate.text.isNotEmpty()) {
                val newText = expiryDate.text.dropLast(1)
                expiryDate = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                expiryDateError = !validateExpiryDate(inputValue = newText, currentYear = 25)
            }
            TextFieldType.CVV -> if (cvvCode.text.isNotEmpty()) {
                val newText = cvvCode.text.dropLast(1)
                cvvCode = TextFieldValue(
                    text = newText,
                    selection = TextRange(newText.length)
                )
                cvvCodeError = !validateCVVCode(inputValue = newText)
            }
            null -> { }
        }
    }

    Column(
        modifier = Modifier.padding(vertical = 48.dp, horizontal = 24.dp)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    localFocusManager.clearFocus()
                    focusedField = null
                }
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Yeni Kart",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = SpaceGroteskFontFamily,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.25F)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(6.dp),
                    colors = CardColors(
                        containerColor = CardYellow,
                        contentColor = CardYellow,
                        disabledContainerColor = CardYellow,
                        disabledContentColor = CardYellow
                    ),
                    border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Black))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CardNumberTextField(
                            modifier = Modifier.fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) focusedField = TextFieldType.CARD_NUMBER
                                },
                            value = cardNumber,
                            error = cardNumberError
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ExpiryDateTextField(
                                modifier = Modifier
                                    .width(LocalConfiguration.current.screenWidthDp.dp * 0.30F)
                                    .onFocusChanged { focusState ->
                                        if (focusState.isFocused) focusedField = TextFieldType.EXPIRY_DATE
                                    },
                                value = expiryDate,
                                error = expiryDateError
                            )
                            CVVCodeTextField(
                                modifier = Modifier
                                    .width(LocalConfiguration.current.screenWidthDp.dp * 0.24F)
                                    .onFocusChanged { focusState ->
                                        if (focusState.isFocused) focusedField = TextFieldType.CVV
                                    },
                                value = cvvCode,
                                error = cvvCodeError
                            )
                        }
                    }
                }
            }
            CustomNumberKeyboard(
                modifier = Modifier.fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.30F),
                onNumberClicked = { number -> onNumberClicked(number) },
                onDeleteClicked = { onDeleteClicked() }
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonYellow,
                contentColor = Color.Black
            ),
            onClick = { }
        ) {
            Text(
                text = "Kartı Əlavə Et",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = SpaceGroteskFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}

fun validateCardNumber(inputValue : String) : Boolean {
    val digitsOnly = inputValue.filter { input -> input.isDigit() }
    return digitsOnly.length == 16
}

fun validateExpiryDate(inputValue : String, currentYear : Int) : Boolean {
    val digitsOnly = inputValue.filter { input -> input.isDigit() }
    if (digitsOnly.length != 4) return false

    val month = digitsOnly.take(2).toInt()
    val year = digitsOnly.drop(2).toInt()

    return month in 1..12 && year >= currentYear
}

fun validateCVVCode(inputValue : String) : Boolean {
    return inputValue.length == 3 && inputValue.all { input -> input.isDigit() }
}

enum class TextFieldType {
    CARD_NUMBER,
    EXPIRY_DATE,
    CVV
}