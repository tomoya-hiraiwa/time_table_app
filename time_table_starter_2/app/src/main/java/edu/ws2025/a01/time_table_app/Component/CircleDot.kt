package edu.ws2025.a01.time_table_app.Component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme

//タスクの左側に表示するドット
@Composable
fun CircleDot(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawCircle(color = color)
    }
}

@Preview
@Composable
private fun CircleDotPreview() {
    Time_table_appTheme {
        CircleDot(modifier = Modifier.size(16.dp), color = Color.Red)
    }
}