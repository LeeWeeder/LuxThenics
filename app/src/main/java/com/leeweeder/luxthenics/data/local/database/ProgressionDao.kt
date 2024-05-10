package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.Progression
import com.leeweeder.luxthenics.domain.model.Routine
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressionDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        "WITH RECURSIVE ordered_progression AS (SELECT id, level, parentProgressionId, exerciseId, name, printf(\"%03d\", level) AS path FROM progression WHERE parentProgressionId IS NULL AND exerciseId = :exerciseId UNION ALL SELECT p.id, p.level, p.parentProgressionId, p.exerciseId, p.name, op.path || '.' || printf(\"%03d\", p.level) FROM progression p INNER JOIN ordered_progression op ON p.parentProgressionId = op.id) SELECT * FROM ordered_progression ORDER BY path;"
    )
    fun getAllProgressionByExerciseId(exerciseId: Int): Flow<List<Progression>>

    @Insert
    suspend fun insertProgression(progression: Progression)

    @Update
    suspend fun updateProgression(progression: Progression)

    @Delete
    suspend fun deleteProgression(progression: Progression)
}