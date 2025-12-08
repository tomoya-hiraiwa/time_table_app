package edu.ws2025.a01.time_table_app.Component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme

@Composable
fun AddTaskListItem(
    modifier: Modifier = Modifier,
    index: Int,
    isChecked: Boolean,
    taskName: String,
    onChecked: (Pair<Int, String>) -> Unit,
    onDeleted: (Int) -> Unit
) {
    var value by remember { mutableStateOf(taskName) }
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isChecked) {
                TextField(
                    onValueChange = {
                        value = it
                    },
                    value = value
                )
            } else {
                Text(
                    value
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            if (!isChecked) {
                IconButton({
                    onChecked(Pair(index, value))
                }) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            } else {
                IconButton({
                    onDeleted(index)
                }) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddTaskListItemPreview() {
    Time_table_appTheme {
        AddTaskListItem(
            index = 1,
            isChecked = false,
            taskName = "test",
            onChecked = {}
        ) {}
    }
}
