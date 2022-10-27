package com.vallem.composeide.ui.components.code

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CodeEditorScope.CodeLine(index: Int, content: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val textStyle = LocalTextStyle.current

    var codeContent by remember { mutableStateOf(content) }

    BasicTextField(
        value = codeContent,
        onValueChange = { codeContent = it },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = if (isLastLine(index)) ImeAction.Done else ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { currentLine = index + 1 },
            onDone = { keyboardController?.hide() }
        ),
        textStyle = TextStyle(
            color = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) onSurface else onSurfaceVariant }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(with(MaterialTheme.colorScheme) { if (isLineSelected(index)) surface else surfaceVariant })
            .onFocusEvent { if (it.isFocused) currentLine = index },
    ) { innerTextField ->
        TextFieldDecorationBox(
            value = codeContent,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = true,
            visualTransformation = VisualTransformation.None,    // TODO syntax highlighting
            interactionSource = MutableInteractionSource(),
            leadingIcon = {
                Text(
                    text = (index + 1).toString(),
                    modifier = Modifier.padding(0.dp)
                )
            },
            contentPadding = PaddingValues()
        )
    }
}

//TextFieldDefaults.textFieldColors(
//  focusedIndicatorColor = Color.Transparent,
//  unfocusedIndicatorColor = Color.Transparent,
//  disabledIndicatorColor = Color.Transparent,
//  errorIndicatorColor = Color.Transparent,
//  containerColor = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) surface else surfaceVariant },
//  textColor = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) onSurface else onSurfaceVariant },
//  focusedLeadingIconColor = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) onSurface else onSurfaceVariant },
//  disabledLeadingIconColor = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) onSurface else onSurfaceVariant },
//  unfocusedLeadingIconColor = with(MaterialTheme.colorScheme) { if (isLineSelected(index)) onSurface else onSurfaceVariant }
//)
