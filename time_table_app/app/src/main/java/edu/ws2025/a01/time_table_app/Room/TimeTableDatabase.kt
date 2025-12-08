package edu.ws2025.a01.time_table_app.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SubjectEntity::class, TaskEntity::class],
    version = 1,
)

abstract class TimeTableDatabase: RoomDatabase(){
    abstract fun subjectDao(): SubjectDao
}