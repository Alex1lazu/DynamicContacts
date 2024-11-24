package com.example.rfbank.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rfbank.ui.theme.RFRed

@Composable
fun EditButton(modifier: Modifier = Modifier, padding: Dp = 40.dp, size: Dp = 60.dp) {
    IconButton(
        onClick = { },
        modifier = modifier
            .padding(padding)
            .size(size),
        colors = IconButtonDefaults.iconButtonColors()
            .copy(containerColor = RFRed, contentColor = Color.White)
    ) {
        Icon(
            imageVector = Icons.Filled.Edit, contentDescription = "",
            modifier = Modifier.fillMaxSize(0.6f),
        )
    }
}