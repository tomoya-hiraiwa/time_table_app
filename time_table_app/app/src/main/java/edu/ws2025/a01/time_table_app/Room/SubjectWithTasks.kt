package edu.ws2025.a01.time_table_app.Room

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectWithTasks(
    @Embedded val subject: SubjectEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId"
    )
    val tasks: List<TaskEntity>
)