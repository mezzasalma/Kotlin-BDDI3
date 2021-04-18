package com.mezzasalma.neighbors.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.mezzasalma.neighbors.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun add(vararg neighborEntity: NeighborEntity)

    @Delete
    fun delete(vararg neighborEntity: NeighborEntity)

    @Update
    fun update(vararg neighborEntity: NeighborEntity)
}
