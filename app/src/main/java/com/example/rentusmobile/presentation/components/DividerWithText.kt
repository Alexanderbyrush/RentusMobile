package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentusmobile.presentation.theme.Border
import com.example.rentusmobile.presentation.theme.TextSecondary

@Composable
fun DividerWithText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(modifier = Modifier.weight(1f).height(1.dp).background(Border))
        Text(text = text, color = TextSecondary, modifier = Modifier.padding(horizontal = 4.dp))
        Box(modifier = Modifier.weight(1f).height(1.dp).background(Border))
    }
}
