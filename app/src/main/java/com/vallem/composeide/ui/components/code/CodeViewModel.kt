package com.vallem.composeide.ui.components.code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.File

class CodeViewModel : ViewModel() {
    var currentFile by mutableStateOf<File?>(null)
        private set
    val currentContent = mutableStateListOf<String>()

    fun openFile(file: File) {
        currentFile = file
        currentContent.clear()
        currentContent.addAll(file.readLines())
    }

    fun openFile(br: BufferedReader) {
//        currentFile = file
        currentContent.clear()
        currentContent.addAll(br.readLines())
    }
}