package edu.ws2025.a01.time_table_app.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ws2025.a01.time_table_app.Room.SubjectWithTasks
import edu.ws2025.a01.time_table_app.Room.TaskEntity
import edu.ws2025.a01.time_table_app.Room.TimetableRepository
import edu.ws2025.a01.time_table_app.data.CalendarDay
import edu.ws2025.a01.time_table_app.data.TaskData
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

//HomePage用ViewModel
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TimetableRepository
) : ViewModel() {
    private val _selectMonth = MutableStateFlow("2026.1")
    private val _targetDate = MutableStateFlow(LocalDate.now())
    private val _targetDateText = MutableStateFlow("2026/1/1 (Sun)")

    val selectMonth = _selectMonth.asStateFlow()
    val targetDate = _targetDate.asStateFlow()
    val targetDateText = _targetDateText.asStateFlow()

    val monthCalendar = targetDate
        .map { date -> generateMonthDays(date) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun setDefaultDate() {
        val today = LocalDate.now()
        val selectMonthFormat = DateTimeFormatter.ofPattern("yyyy.M")
        val targetDateFormat = DateTimeFormatter.ofPattern("yyyy/M/d (EEE)")
        _selectMonth.value = selectMonthFormat.format(today)
        _targetDateText.value = targetDateFormat.format(today)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val subjectWithTasks = targetDate.flatMapLatest { date ->
        println(date)
        repository.getSubjectWithTasks(date)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun selectedDate(timeStamp: Long) {
        val instant = Timestamp(timeStamp).toInstant()
        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        _targetDate.value = date
        val targetDateTextFormat = DateTimeFormatter.ofPattern("yyyy/M/d (EEE)")
        _targetDateText.value = targetDateTextFormat.format(_targetDate.value)
        val monthDateFormat = DateTimeFormatter.ofPattern("yyyy.M")
        _selectMonth.value = monthDateFormat.format(date)
    }

    fun onTaskDoneChanged(taskData: TaskEntity) {
        viewModelScope.launch {
            repository.updateTaskDone(taskData)
        }

    }

    fun selectTargetDate(data: CalendarDay) {
        _targetDate.value =
            LocalDate.of(targetDate.value.year, targetDate.value.month, data.date.toInt())
        val targetDateTextFormat = DateTimeFormatter.ofPattern("yyyy/M/d (EEE)")
        _targetDateText.value = targetDateTextFormat.format(_targetDate.value)
    }


    private fun generateMonthDays(baseDate: LocalDate): List<CalendarDay> {
        var dateList = mutableListOf<CalendarDay>()
        val last = baseDate.lengthOfMonth()
        for (day in 1..last) {
            val date = baseDate.withDayOfMonth(day)
            val dateFormatter = DateTimeFormatter.ofPattern("d")
            val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE")
            dateList.add(
                CalendarDay(
                    date = dateFormatter.format(date),
                    dayOfWeek = dayOfWeekFormatter.format(date)
                )
            )
        }
        return dateList
    }
}