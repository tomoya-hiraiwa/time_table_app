package edu.ws2025.a01.time_table_app.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ws2025.a01.time_table_app.data.SubjectData
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme
import edu.ws2025.a01.time_table_app.ui.theme.kokugoColor

@Composable
fun SubjectIcon(
    modifier: Modifier = Modifier,
    data: SubjectData,
    isSelected: Boolean,
    onClick: (SubjectData) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) Color.Red else Color.Transparent
            )
            .background(
                data.color
            )
            .clickable {
                onClick(data)
            }
    ) {
        Text(
            data.subject, style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview
@Composable
private fun SubjectIconPreview() {
    Time_table_appTheme {
        SubjectIcon(isSelected = false, data = SubjectData("国語", kokugoColor)) {}
    }
}