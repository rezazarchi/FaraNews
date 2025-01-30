package ir.rezazarchi.metamovie.features.details.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun StatisticItem(icon: ImageVector, count: Int?, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
        )
        Text(
            text = "$count",
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = label,
            fontSize = 12.sp,
        )
    }
}