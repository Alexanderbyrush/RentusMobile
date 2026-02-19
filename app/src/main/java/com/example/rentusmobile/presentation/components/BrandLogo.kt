package com.example.rentusmobile.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentusmobile.R
import com.example.rentusmobile.presentation.theme.TextPrimary

@Composable
fun BrandLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logodark),
            contentDescription = "RentUs logo",
            modifier = Modifier.size(38.dp)
        )
        Text(text = "Rent", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, color = TextPrimary)
        Text(text = "Us", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, color = TextPrimary)
    }
}
