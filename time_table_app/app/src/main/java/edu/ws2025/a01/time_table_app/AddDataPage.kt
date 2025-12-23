package edu.ws2025.a01.time_table_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ws2025.a01.time_table_app.Component.AddTaskListItem
import edu.ws2025.a01.time_table_app.Component.DropDown
import edu.ws2025.a01.time_table_app.Component.SubjectIcon
import edu.ws2025.a01.time_table_app.Room.SubjectEntity
import edu.ws2025.a01.time_table_app.ViewModel.AddDataViewModel
import edu.ws2025.a01.time_table_app.data.subjectList
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//データ追加画面用Composable関数
@Composable
fun AddDataPage(
    modifier: Modifier = Modifier, targetDate: LocalDate,
    viewModel: AddDataViewModel = hiltViewModel(),
    onPageBack: () -> Unit
) {
    val periodList = List(6) { it + 1 }
    val targetDateText = remember{targetDate.format(DateTimeFormatter.ofPattern("yyyy/M/d (EEE)"))}
    val selectedSubject by viewModel.selectedSubject.collectAsStateWithLifecycle()
    val selectedPeriod by viewModel.selectedPeriod.collectAsStateWithLifecycle()
    val taskList by viewModel.taskList.collectAsStateWithLifecycle()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton({
                onPageBack()
            }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            TextButton({
                viewModel.addTask(
                    subject = SubjectEntity(
                        date = targetDateText,
                        period = selectedPeriod,
                        subjectName = selectedSubject
                    ),
                    taskList = taskList
                )
            }) {
                Text("登録")
            }
        }
        Text(
            targetDateText,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )
        DropDown(options = periodList, selectedValue = selectedPeriod) {
            viewModel.selectPeriod(it)
        }
        Text(
            "教科", modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 4),
            contentPadding = PaddingValues(4.dp),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(subjectList) {
                SubjectIcon(
                    data = it,
                    isSelected = it.subject == selectedSubject
                ) {
                    viewModel.selectSubject(it.subject)
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("リスト")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            IconButton(
                {
                    viewModel.addTaskItems()
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
        HorizontalDivider()
        LazyColumn(modifier = Modifier.padding(top = 12.dp), contentPadding = PaddingValues(4.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            itemsIndexed(taskList) { index, item ->
                AddTaskListItem(
                    index = index,
                    isChecked = taskList[index].isChecked,
                    taskName = taskList[index].taskName,
                    onChecked = {
                        viewModel.changeTaskItems(it)
                    }
                ) {
                    viewModel.deleteTaskItems(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddDataPagePreview() {
    Time_table_appTheme {
        AddDataPage(targetDate = LocalDate.now()) {}
    }
}