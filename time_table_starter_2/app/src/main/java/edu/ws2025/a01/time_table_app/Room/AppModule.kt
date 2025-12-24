package edu.ws2025.a01.time_table_app.Room

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

//データベース作成用コード
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): TimeTableDatabase = Room.databaseBuilder(
        app,
        TimeTableDatabase::class.java,
        "timetable.db"
    ).build()

    @Provides
    fun providedDao(db: TimeTableDatabase): SubjectDao = db.subjectDao()

    @Provides
    @Singleton
    fun provideTimetableRepository(dao: SubjectDao): TimetableRepository = TimetableRepository(dao)
}