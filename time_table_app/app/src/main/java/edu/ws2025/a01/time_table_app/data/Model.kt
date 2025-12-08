package edu.ws2025.a01.time_table_app.data

import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek

data class SubjectData(
    val subject: String,
    val color: Color
)

data class CalendarDay(
    val date: String,
    val dayOfWeek: String
)

data class TaskData(
    var taskName: String,
    var isChecked: Boolean = false
)

