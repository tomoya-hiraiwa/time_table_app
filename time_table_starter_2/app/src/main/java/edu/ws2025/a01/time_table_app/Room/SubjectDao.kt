package edu.ws2025.a01.time_table_app.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

//Database Access Object(データベースへのリクエストクエリを集めたもの)
@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT* FROM subjects WHERE date = :date AND period = :period LIMIT 1")
    suspend fun findSubject(date: String, period: Int): SubjectEntity?

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)

    @Query("DELETE FROM tasks WHERE subjectId = :subjectId")
    suspend fun deleteTasksBySubjectId(subjectId: Int)

    @Transaction
    @Query("SELECT * FROM subjects WHERE date = :date ORDER BY period ASC")
    fun getSubjectsWithTasks(date: String): Flow<List<SubjectWithTasks>>

    @Query("UPDATE tasks SET isDone = :isDone WHERE id = :taskId")
    suspend fun updateTaskDone(taskId: Int, isDone: Boolean)
}