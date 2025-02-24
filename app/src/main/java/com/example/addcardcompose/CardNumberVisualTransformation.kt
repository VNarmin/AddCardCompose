package com.example.addcardcompose

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation : VisualTransformation {
    override fun filter(text : AnnotatedString) : TransformedText {
        // formatting the card number by chunking into groups of 4 characters, separated by spaces
        val formattedText = text.text.chunked(4).joinToString(" ")

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = CardNumberOffsetMapping
        )
    }

    private object CardNumberOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset : Int) : Int {
            var spaceCount = 0
            // calculating the number of spaces inserted before the given offset
            for (i in 0 until offset) {
                if (i > 0 && i % 4 == 0) spaceCount++
            }
            return offset + spaceCount
        }

        override fun transformedToOriginal(offset : Int) : Int {
            var spaceCount = 0
            // calculating the number of spaces before the given transformed offset
            for (i in 0 until offset) {
                if (i > 0 && i % 5 == 4) spaceCount++
            }
            return offset - spaceCount
        }
    }
}