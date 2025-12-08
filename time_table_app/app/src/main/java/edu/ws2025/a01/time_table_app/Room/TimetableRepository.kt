package edu.ws2025.a01.time_table_app.Room

import edu.ws2025.a01.time_table_app.data.TaskData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TimetableRepository @Inject constructor(
    private val dao: SubjectDao
){
    fun getSubjectWithTasks(date: LocalDate): Flow<List<SubjectWithTasks>> {
        val dateText = date.format(DateTimeFormatter.ofPattern("yyyy/M/d (EEE)"))
        return dao.getSubjectsWithTasks(dateText)
        }

        suspend fun updateTaskDone(data: TaskEntity) {
            dao.updateTaskDone(data.id, !data.isDone)
        }

        suspend fun addSubjectWithTasks(
            subject: SubjectEntity,
            tasks: List<TaskData>
        ) {

            val existing = dao.findSubject(subject.date, subject.period)
            if (existing != null){
                dao.deleteSubject(existing)
            }
            val subjectId = dao.insertSubject(subject).toInt()

            tasks.forEach {
                dao.insertTask(
                    TaskEntity(
                        subjectId = subjectId,
                        taskName = it.taskName
                    )
                )
            }
        }
}