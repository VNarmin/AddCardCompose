package com.example.addcardcompose

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiryDateTransformation : VisualTransformation {
    override fun filter(text : AnnotatedString) : TransformedText {
        val formattedText = if (text.text.length > 2) "${text.text.take(2)}/${text.text.drop(2)}"
        else text.text

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = ExpiryDateOffsetMapping
        )
    }

    private object ExpiryDateOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset : Int) : Int {
            // adjusting the cursor position to account for the slash (position 2)
            return if (offset <= 2) offset else offset + 1
        }

        override fun transformedToOriginal(offset : Int) : Int {
            // adjusting the cursor position back to the original value (removing the slash)
            return if (offset <= 2) offset else offset - 1
        }
    }
}