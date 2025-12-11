package edu.ws2025.a01.time_table_app.Component

import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ws2025.a01.time_table_app.Room.SubjectEntity
import edu.ws2025.a01.time_table_app.Room.SubjectWithTasks
import edu.ws2025.a01.time_table_app.Room.TaskEntity
import edu.ws2025.a01.time_table_app.data.TaskData
import edu.ws2025.a01.time_table_app.data.subjectList
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme

//教科リストのアイテムコンポーネント
@Composable
fun SubjectListItem(
    modifier: Modifier = Modifier,
    data: SubjectWithTasks,
    onDoneChanged: (TaskEntity) -> Unit
) {
    val color = subjectList.find { it.subject == data.subject.subjectName }?.color
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier.background(color ?: Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    data.subject.subjectName,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            data.tasks.forEach {
                SubjectCheckListItem(
                    color = color ?: Color.Red,
                    data = it,
                ) {
                    onDoneChanged(it)
                }
            }
        }
    }
}


@Composable
fun SubjectCheckListItem(
    modifier: Modifier = Modifier,
    color: Color,
    data: TaskEntity,
    onDoneChanged: (TaskEntity) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleDot(modifier = Modifier.size(16.dp), color = color)
        Text(
            data.taskName,
            modifier = Modifier.padding(start = 8.dp),
            textDecoration = if (data.isDone) TextDecoration.LineThrough else TextDecoration.None
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Checkbox(
            checked = data.isDone,
            onCheckedChange = {
                onDoneChanged(data)
            },
        )
    }
}


@Preview
@Composable
private fun SubjectListItemPreview() {
    Time_table_appTheme {
        SubjectListItem(
            data = SubjectWithTasks(
                SubjectEntity(
                    id = 0,
                    date = "2025/1/1 (Sun)",
                    period = 1,
                    subjectName = "国語"
                ), tasks = emptyList()
            )
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubjectCheckListItemPreview() {
    Time_table_appTheme {
        SubjectCheckListItem(
            color = Color.Red,
            data = TaskEntity(
                id = 0,
                subjectId = 0,
                taskName = "task1",
                isDone = false
            )
        ) {}
    }
}