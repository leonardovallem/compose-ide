package com.vallem.composeide.ui.screens.project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
class ProjectScreenArgs(val projectDirectory: File) : Parcelable
