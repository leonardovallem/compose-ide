package com.vallem.composeide.util

import android.net.Uri
import android.provider.DocumentsContract.buildDocumentUriUsingTree
import android.provider.DocumentsContract.getTreeDocumentId


fun Uri.buildDocumentUriUsingTree() = buildDocumentUriUsingTree(this, getTreeDocumentId(this))