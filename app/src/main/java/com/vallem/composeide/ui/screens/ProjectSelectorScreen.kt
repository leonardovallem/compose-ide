package com.vallem.composeide.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import com.vallem.composeide.ui.onFailure
import com.vallem.composeide.ui.onSuccess
import com.vallem.composeide.ui.screens.destinations.CreateProjectBottomSheetDestination
import com.vallem.composeide.ui.screens.destinations.ProjectScreenDestination
import com.vallem.composeide.ui.screens.project.ProjectScreenArgs
import com.vallem.composeide.util.createProject
import com.vallem.composeide.util.rememberProjectsDirectory
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Destination(
    start = true,
    route = Routes.SelectFile,
)
@Composable
fun SelectFileScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CreateProjectBottomSheetDestination, String>
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val directory = rememberProjectsDirectory()
    val projects by remember { mutableStateOf(directory.listFiles() ?: emptyArray()) }

    resultRecipient.onNavResult {
        if (it is NavResult.Value) directory.createProject(it.value)
            .onSuccess<File> { file ->
                val args = ProjectScreenArgs(file)
                navigator.navigate(ProjectScreenDestination(args))
            }
            .onFailure<String> { message ->
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton({ navigator.navigate(CreateProjectBottomSheetDestination) }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Create new project"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Projects") }) }
    ) { pv ->
        LazyColumn(modifier = Modifier.padding(pv)) {
            items(projects) {
                Text(text = it.name)
            }
        }
    }
}

