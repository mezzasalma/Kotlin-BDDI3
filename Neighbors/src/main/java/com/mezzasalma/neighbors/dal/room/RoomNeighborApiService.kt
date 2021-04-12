package com.mezzasalma.neighbors.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mezzasalma.neighbors.dal.NeighborApiService
import com.mezzasalma.neighbors.dal.room.daos.NeighborDao
import com.mezzasalma.neighbors.dal.utilis.toNeighbor
import com.mezzasalma.neighbors.models.Neighbor

class RoomNeighborApiService(application: Application) : NeighborApiService {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun createneighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateDataNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
