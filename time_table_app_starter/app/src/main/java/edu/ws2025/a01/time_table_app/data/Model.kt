package edu.ws2025.a01.time_table_app.data

import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
//TODO 5.データクラスの作成
//教科名とテーマカラーをまとめたデータクラス　

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

