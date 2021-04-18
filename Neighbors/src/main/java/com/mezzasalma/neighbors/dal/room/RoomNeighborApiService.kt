package com.mezzasalma.neighbors.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mezzasalma.neighbors.dal.NeighborApiService
import com.mezzasalma.neighbors.dal.room.daos.NeighborDao
import com.mezzasalma.neighbors.dal.utilis.toEntity
import com.mezzasalma.neighbors.dal.utilis.toNeighbor
import com.mezzasalma.neighbors.models.Neighbor
import java.util.concurrent.Executors

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
        Executors.newSingleThreadExecutor().execute {
            dao.delete(neighbor.toEntity())
        }
    }

    override fun createneighbor(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            dao.add(neighbor.toEntity())
        }
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            neighbor.favorite = !neighbor.favorite
            dao.update(neighbor.toEntity())
        }
    }

    override fun updateDataNeighbor(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            dao.update(neighbor.toEntity())
        }
    }
}
