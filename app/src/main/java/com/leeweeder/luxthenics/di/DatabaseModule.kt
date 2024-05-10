package com.leeweeder.luxthenics.di

import android.content.Context
import androidx.room.Room
import com.leeweeder.luxthenics.data.local.database.AppDatabase
import com.leeweeder.luxthenics.data.local.database.ExerciseDao
import com.leeweeder.luxthenics.data.local.repository.ExerciseGroupRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.ExerciseLogRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.ExerciseRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.ProgressionRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.RoutineExerciseRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.RoutineRepositoryImpl
import com.leeweeder.luxthenics.data.local.repository.TargetRepositoryImpl
import com.leeweeder.luxthenics.domain.repository.ExerciseGroupRepository
import com.leeweeder.luxthenics.domain.repository.ExerciseLogRepository
import com.leeweeder.luxthenics.domain.repository.ExerciseRepository
import com.leeweeder.luxthenics.domain.repository.ProgressionRepository
import com.leeweeder.luxthenics.domain.repository.RoutineExerciseRepository
import com.leeweeder.luxthenics.domain.repository.RoutineRepository
import com.leeweeder.luxthenics.domain.repository.TargetRepository
import com.leeweeder.luxthenics.domain.usecases.DeleteExercise
import com.leeweeder.luxthenics.domain.usecases.DeleteExerciseGroup
import com.leeweeder.luxthenics.domain.usecases.DeleteExerciseLog
import com.leeweeder.luxthenics.domain.usecases.DeleteProgression
import com.leeweeder.luxthenics.domain.usecases.DeleteRoutine
import com.leeweeder.luxthenics.domain.usecases.DeleteRoutineExercise
import com.leeweeder.luxthenics.domain.usecases.DeleteTarget
import com.leeweeder.luxthenics.domain.usecases.ExerciseGroupUseCases
import com.leeweeder.luxthenics.domain.usecases.ExerciseLogUseCases
import com.leeweeder.luxthenics.domain.usecases.ExerciseUseCases
import com.leeweeder.luxthenics.domain.usecases.GetExerciseById
import com.leeweeder.luxthenics.domain.usecases.GetExerciseGroupById
import com.leeweeder.luxthenics.domain.usecases.GetExerciseLogs
import com.leeweeder.luxthenics.domain.usecases.GetExerciseLogsByExerciseId
import com.leeweeder.luxthenics.domain.usecases.GetExerciseLogsByRoutineId
import com.leeweeder.luxthenics.domain.usecases.GetExerciseWithTargets
import com.leeweeder.luxthenics.domain.usecases.GetExercises
import com.leeweeder.luxthenics.domain.usecases.GetProgressionsByExerciseId
import com.leeweeder.luxthenics.domain.usecases.GetRoutineById
import com.leeweeder.luxthenics.domain.usecases.GetRoutineExerciseById
import com.leeweeder.luxthenics.domain.usecases.GetRoutineExercises
import com.leeweeder.luxthenics.domain.usecases.GetRoutineExercisesByRoutineId
import com.leeweeder.luxthenics.domain.usecases.GetRoutines
import com.leeweeder.luxthenics.domain.usecases.GetTargetById
import com.leeweeder.luxthenics.domain.usecases.GetTargetWithRoutines
import com.leeweeder.luxthenics.domain.usecases.GetTargets
import com.leeweeder.luxthenics.domain.usecases.InsertExercise
import com.leeweeder.luxthenics.domain.usecases.InsertExerciseGroup
import com.leeweeder.luxthenics.domain.usecases.InsertExerciseLog
import com.leeweeder.luxthenics.domain.usecases.InsertProgression
import com.leeweeder.luxthenics.domain.usecases.InsertRoutine
import com.leeweeder.luxthenics.domain.usecases.InsertRoutineExercise
import com.leeweeder.luxthenics.domain.usecases.InsertTarget
import com.leeweeder.luxthenics.domain.usecases.ProgressionUseCases
import com.leeweeder.luxthenics.domain.usecases.RoutineExerciseUseCases
import com.leeweeder.luxthenics.domain.usecases.RoutineUseCases
import com.leeweeder.luxthenics.domain.usecases.TargetUseCases
import com.leeweeder.luxthenics.domain.usecases.UpdateExercise
import com.leeweeder.luxthenics.domain.usecases.UpdateExerciseGroup
import com.leeweeder.luxthenics.domain.usecases.UpdateExerciseLog
import com.leeweeder.luxthenics.domain.usecases.UpdateProgression
import com.leeweeder.luxthenics.domain.usecases.UpdateRoutine
import com.leeweeder.luxthenics.domain.usecases.UpdateRoutineExercise
import com.leeweeder.luxthenics.domain.usecases.UpdateTarget
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
        return appDatabase.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(db: AppDatabase): ExerciseRepository {
        return ExerciseRepositoryImpl(db.exerciseDao())
    }

    @Provides
    @Singleton
    fun provideExerciseGroupRepository(db: AppDatabase): ExerciseGroupRepository {
        return ExerciseGroupRepositoryImpl(db.exerciseGroupDao())
    }

    @Provides
    @Singleton
    fun provideExerciseLogRepository(db: AppDatabase): ExerciseLogRepository {
        return ExerciseLogRepositoryImpl(db.exerciseLogDao())
    }

    @Provides
    @Singleton
    fun provideProgressionRepository(db: AppDatabase): ProgressionRepository {
        return ProgressionRepositoryImpl(db.progressionDao())
    }

    @Provides
    @Singleton
    fun provideRoutineRepository(db: AppDatabase): RoutineRepository {
        return RoutineRepositoryImpl(db.routineDao())
    }

    @Provides
    @Singleton
    fun provideTargetRepository(db: AppDatabase): TargetRepository {
        return TargetRepositoryImpl(db.targetDao())
    }

    @Provides
    @Singleton
    fun provideTargetUseCases(repository: TargetRepository): TargetUseCases {
        return TargetUseCases(
            getTargets = GetTargets(repository),
            getTargetById = GetTargetById(repository),
            insertTarget = InsertTarget(repository),
            updateTarget = UpdateTarget(repository),
            deleteTarget = DeleteTarget(repository),
            getTargetWithRoutines = GetTargetWithRoutines(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRoutineExerciseRepository(db: AppDatabase): RoutineExerciseRepository {
        return RoutineExerciseRepositoryImpl(db.routineExerciseDao())
    }

    @Provides
    @Singleton
    fun provideExerciseUseCases(repository: ExerciseRepository): ExerciseUseCases {
        return ExerciseUseCases(
            getExercises = GetExercises(repository),
            getExerciseById = GetExerciseById(repository),
            insertExercise = InsertExercise(repository),
            updateExercise = UpdateExercise(repository),
            deleteExercise = DeleteExercise(repository),
            getExerciseWithTargets = GetExerciseWithTargets(repository)
        )
    }

    @Provides
    @Singleton
    fun provideExerciseGroupUseCases(repository: ExerciseGroupRepository): ExerciseGroupUseCases {
        return ExerciseGroupUseCases(
            getExerciseGroupById = GetExerciseGroupById(repository),
            insertExerciseGroup = InsertExerciseGroup(repository),
            updateExerciseGroup = UpdateExerciseGroup(repository),
            deleteExerciseGroup = DeleteExerciseGroup(repository)
        )
    }

    @Provides
    @Singleton
    fun provideExerciseLogUseCases(repository: ExerciseLogRepository): ExerciseLogUseCases {
        return ExerciseLogUseCases(
            getExerciseLogs = GetExerciseLogs(repository),
            getExerciseLogsByExerciseId = GetExerciseLogsByExerciseId(repository),
            getExerciseLogByRoutineId = GetExerciseLogsByRoutineId(repository),
            insertExerciseLog = InsertExerciseLog(repository),
            updateExerciseLog = UpdateExerciseLog(repository),
            deleteExerciseLog = DeleteExerciseLog(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProgressionUseCases(repository: ProgressionRepository): ProgressionUseCases {
        return ProgressionUseCases(
            getProgressionsByExerciseId = GetProgressionsByExerciseId(repository),
            insertProgression = InsertProgression(repository),
            updateProgression = UpdateProgression(repository),
            deleteProgression = DeleteProgression(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRoutineUseCases(repository: RoutineRepository): RoutineUseCases {
        return RoutineUseCases(
            getRoutines = GetRoutines(repository),
            getRoutineById = GetRoutineById(repository),
            insertRoutine = InsertRoutine(repository),
            updateRoutine = UpdateRoutine(repository),
            deleteRoutine = DeleteRoutine(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRoutineExerciseUseCases(repository: RoutineExerciseRepository): RoutineExerciseUseCases {
        return RoutineExerciseUseCases(
            getRoutineExercises = GetRoutineExercises(repository),
            getRoutineExercisesByRoutineId = GetRoutineExercisesByRoutineId(repository),
            getRoutineExerciseById = GetRoutineExerciseById(repository),
            insertRoutineExercise = InsertRoutineExercise(repository),
            updateRoutineExercise = UpdateRoutineExercise(repository),
            deleteRoutineExercise = DeleteRoutineExercise(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}
