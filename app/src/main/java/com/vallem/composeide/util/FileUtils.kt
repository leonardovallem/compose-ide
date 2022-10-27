package com.vallem.composeide.util

import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.anggrayudi.storage.file.child
import com.vallem.composeide.ui.ScreenState
import java.io.File

private const val DIRECTORY_NAME = "Compose Projects"

fun projectsDirectory() = Environment
    .getExternalStorageDirectory()
    .child(DIRECTORY_NAME).apply {
        if (!exists()) mkdir()
    }

fun File.createProject(name: String) = child(name).let {
    if (it.exists()) ScreenState.Failure("File already exists")
    else {
        it.mkdir()
        ScreenState.Success(it)
    }
}

@Composable
fun rememberProjectsDirectory() = remember {
    projectsDirectory()
}