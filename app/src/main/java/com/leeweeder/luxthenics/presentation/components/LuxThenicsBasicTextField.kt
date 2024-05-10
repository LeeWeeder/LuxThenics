package com.leeweeder.luxthenics.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuxThenicsBasicTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.titleLarge.copy(
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            fontWeight = FontWeight.Bold
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                if (value.text.isEmpty()) {
                    if (placeholder != null)
                        Text(
                            text = placeholder,
                            color = TextFieldDefaults.colors().unfocusedPlaceholderColor.copy(
                                alpha = 0.5f
                            ),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                }
                innerTextField()
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuxThenicsBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = TopAppBarDefaults.topAppBarColors().titleContentColor,
        fontWeight = FontWeight.Bold
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                if (value.isEmpty()) {
                    if (placeholder != null)
                        Text(
                            text = placeholder,
                            color = TextFieldDefaults.colors().unfocusedPlaceholderColor.copy(
                                alpha = 0.8f
                            ),
                            style = textStyle.copy(MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                }
                innerTextField()
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}