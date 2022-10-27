package com.vallem.composeide.ui.screens.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.vallem.composeide.ui.components.code.CodeEditor
import com.vallem.composeide.ui.components.code.CodeViewModel
import com.vallem.composeide.ui.components.code.rememberCodeEditorScope
import com.vallem.composeide.ui.screens.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination(route = Routes.Project)
@Composable
fun ProjectScreen(
    args: ProjectScreenArgs,
    viewModel: CodeViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val codeEditorScope = rememberCodeEditorScope(totalLines = viewModel.currentContent.size)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProjectStructure(projectName = args.projectDirectory.name)
        },
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { pv ->
            CodeEditor(
                onOpenProjectStructure = { scope.launch { drawerState.open() } },
                modifier = Modifier.padding(pv),
                viewModel = viewModel
            )
        }
    }
}