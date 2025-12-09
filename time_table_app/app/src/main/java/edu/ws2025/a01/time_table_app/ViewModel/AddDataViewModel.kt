package edu.ws2025.a01.time_table_app.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ws2025.a01.time_table_app.Room.SubjectEntity
import edu.ws2025.a01.time_table_app.Room.TimetableRepository
import edu.ws2025.a01.time_table_app.data.TaskData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//AddDataPage用ViewModel
@HiltViewModel
class AddDataViewModel @Inject constructor(
    private val repository: TimetableRepository
) : ViewModel() {
    private val _selectedSubject = MutableStateFlow("国語")
    private val _selectedPeriod = MutableStateFlow(1)
    private val _taskList = MutableStateFlow<List<TaskData>>(emptyList())

    val selectedSubject = _selectedSubject.asStateFlow()
    val selectedPeriod = _selectedPeriod.asStateFlow()
    val taskList = _taskList.asStateFlow()

    fun selectSubject(subject: String){
        _selectedSubject.value = subject
    }
    fun selectPeriod(period: Int){
        _selectedPeriod.value = period
    }

    fun addTaskItems(){
        _taskList.value = _taskList.value + TaskData("")
    }

    fun changeTaskItems(itemData: Pair<Int, String>){
        _taskList.value = _taskList.value.toMutableList().apply {
            val targetData = this[itemData.first].copy()
            targetData.taskName = itemData.second
            targetData.isChecked = true
            this[itemData.first] = targetData
        }
    }

    fun deleteTaskItems(index: Int){
        _taskList.value = _taskList.value.toMutableList().apply {
            removeAt(index)
        }
    }


    fun addTask(subject: SubjectEntity, taskList: List<TaskData>) {
        viewModelScope.launch {
            repository.addSubjectWithTasks(
                subject,
                taskList
            )
        }
    }
}