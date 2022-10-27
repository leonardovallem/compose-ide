package com.vallem.composeide.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalMaterial3Api::class)
@Destination(
    route = Routes.BottomSheets.CreateProject,
    style = DestinationStyle.BottomSheet::class
)
@Composable
fun CreateProjectBottomSheet(resultBackNavigator: ResultBackNavigator<String>) {
    var projectName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Create new project",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = projectName,
            onValueChange = { projectName = it },
            placeholder = { Text("Project name") },
            modifier = Modifier.fillMaxWidth()
        )

        FilledTonalButton(
            onClick = { resultBackNavigator.navigateBack(projectName) },
            enabled = projectName.isNotBlank(),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Create")
        }
    }
}

@Preview
@Composable
fun CreateProjectBottomSheetPreview() {
    CreateProjectBottomSheet(EmptyResultBackNavigator())
}
