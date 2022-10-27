package com.vallem.composeide.ui.components.code

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CodeEditor(
    onOpenProjectStructure: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CodeViewModel
) {
    val codeEditorScope = rememberCodeEditorScope(totalLines = viewModel.currentContent.size)

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        stickyHeader {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = onOpenProjectStructure) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Open project structure"
                        )
                    }
                },
                title = { Text(viewModel.currentFile?.let { it.name } ?: "No file selected") }
            )
        }

        itemsIndexed(viewModel.currentContent) { index, line ->
            codeEditorScope.CodeLine(index, line)
        }
    }
}

//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CodeEditorPreview() {
//    val code = remember {
//        listOf(
//            "fun main() {",
//            "    println(\"Hello world\")",
//            "}",
//            ""
//        )
//    }
//    CodeEditor(code)
//}
