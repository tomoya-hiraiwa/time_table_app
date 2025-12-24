package edu.ws2025.a01.time_table_app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.ws2025.a01.time_table_app.Component.DateHeader
import edu.ws2025.a01.time_table_app.Component.SubjectListItem
import edu.ws2025.a01.time_table_app.Component.TargetDatePickerDialog
import edu.ws2025.a01.time_table_app.Component.TopCalendar
import edu.ws2025.a01.time_table_app.Room.SubjectEntity
import edu.ws2025.a01.time_table_app.Room.SubjectWithTasks
import edu.ws2025.a01.time_table_app.Room.TaskEntity
import edu.ws2025.a01.time_table_app.ViewModel.HomeViewModel
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.Period

//初期画面用Composable関数
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onAddPressed: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val subjectWithTaskList by viewModel.subjectWithTasks.collectAsStateWithLifecycle()
    val monthCalendar by viewModel.monthCalendar.collectAsStateWithLifecycle()
    val targetDateText by viewModel.targetDateText.collectAsStateWithLifecycle()
    val selectedMonthText by viewModel.selectMonth.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setDefaultDate()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TextButton(
            onClick = {
                showDatePicker = true
            }
        ) {
            Text(
                selectedMonthText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
        //1ヶ月分の日付表示カレンダー
        TopCalendar(calendarDayList = monthCalendar) {
            viewModel.selectTargetDate(it)
        }
        //選択した日付を表示するヘッダー
        DateHeader(
            dateText = targetDateText
        ) {
            onAddPressed(viewModel.targetDate.value)
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .weight(1f)
                .verticalScroll(state = rememberScrollState())
        ) {
            //対応するデータが存在する場合
            if (subjectWithTaskList.isNotEmpty()) {
                subjectWithTaskList.forEach {
                    SubjectGroup(subjectTaskData = it) {
                        viewModel.onTaskDoneChanged(taskData = it)
                    }
                }
            //対応するデータが存在しない場合
            } else {
                Text(
                    "データなし",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
        }


    }
    if (showDatePicker) {
        //日付選択用DatePickerDialog
        TargetDatePickerDialog(onDismiss = { showDatePicker = false }) {
            viewModel.selectedDate(it ?: Instant.now().toEpochMilli())
            showDatePicker = false
        }

    }
}

//各時間の教科表示リストアイテム
@Composable
fun SubjectGroup(
    modifier: Modifier = Modifier,
    subjectTaskData: SubjectWithTasks,
    onDoneChanged: (TaskEntity) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 4.dp),) {
        Text(
            "${subjectTaskData.subject.period}時限目",
            modifier = Modifier.padding(vertical = 4.dp)
        )
        SubjectListItem(
            data = subjectTaskData
        ) {
            onDoneChanged(it)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Preview
@Composable
private fun HomePagePreview() {
    Time_table_appTheme {
        HomePage() {}
    }
}




