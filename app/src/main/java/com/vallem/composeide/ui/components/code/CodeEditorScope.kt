package com.vallem.composeide.ui.components.code

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class CodeEditorScope internal constructor(
    totalLines: Int,
    currentLine: Int,
) {
    var totalLines by mutableStateOf(totalLines)
    var currentLine by mutableStateOf(currentLine)

    fun isLineSelected(line: Int) = currentLine == line
    fun isLastLine(line: Int) = (totalLines - 1) == line
}

@Composable
fun rememberCodeEditorScope(
    totalLines: Int,
    currentLine: Int = 0
) = remember { CodeEditorScope(totalLines, currentLine) }