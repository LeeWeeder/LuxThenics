package com.leeweeder.luxthenics.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leeweeder.luxthenics.domain.model.Exercise
import com.leeweeder.luxthenics.domain.model.ExerciseGroup
import com.leeweeder.luxthenics.domain.model.ExerciseLog
import com.leeweeder.luxthenics.domain.model.Progression
import com.leeweeder.luxthenics.domain.model.Routine
import com.leeweeder.luxthenics.domain.model.RoutineExercise
import com.leeweeder.luxthenics.domain.model.ExerciseTargetCrossRef
import com.leeweeder.luxthenics.domain.model.Target

@Database(
    entities = [
        Exercise::class,
        ExerciseGroup::class,
        ExerciseLog::class,
        Progression::class,
        Routine::class,
        RoutineExercise::class,
        Target::class,
        ExerciseTargetCrossRef::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseGroupDao(): ExerciseGroupDao
    abstract fun exerciseLogDao(): ExerciseLogDao
    abstract fun progressionDao(): ProgressionDao
    abstract fun routineDao(): RoutineDao
    abstract fun routineExerciseDao(): RoutineExerciseDao
    abstract fun targetDao(): TargetDao

    companion object {
        const val DATABASE_NAME = "luxthenics_db"
    }
}
