package edu.ws2025.a01.time_table_app.Room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//教科データのテーブル用データクラス
@Entity(tableName = "subjects",
        indices = [
            Index(value = ["date","period"], unique = true)
        ]
    )
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val period: Int,
    val subjectName: String
)