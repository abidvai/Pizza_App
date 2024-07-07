package com.example.pizzaapp.AppScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomChips(
    title: String,
    selected: Boolean,
    onValueChange: (String) -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color.Red,
            Color.Black
        )
    )

    FilterChip(
        selected = selected,
        onClick = {
            onValueChange(title)
        },
        label = {
            Text(
                text = title,
                fontSize = 20.sp,
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.inversePrimary,
            labelColor = if (selected) Color.Yellow else Color.Black
        ),
        shape = RoundedCornerShape(6.dp),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = if (selected) Color.Black else Color.Black,
            borderWidth = if (selected) 0.dp else 1.dp
        ),
        modifier = Modifier.padding(3.dp)
    )
}