package edu.ws2025.a01.time_table_app.data

import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek

//教科名とテーマカラーをまとめたデータクラス　
data class SubjectData(
    val subject: String,
    val color: Color
)

//カレンダーの日付と曜日をまとめたデータクラス
data class CalendarDay(
    val date: String,
    val dayOfWeek: String
)

//タスクと完了状態をまとめたデータクラス
data class TaskData(
    var taskName: String,
    var isChecked: Boolean = false
)

