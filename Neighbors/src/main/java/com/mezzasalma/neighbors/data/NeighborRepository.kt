package com.mezzasalma.neighbors.data

import androidx.lifecycle.LiveData
import com.mezzasalma.neighbors.data.service.DummyNeighborApiService
import com.mezzasalma.neighbors.data.service.NeighborApiService
import com.mezzasalma.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbors(): LiveData<List<Neighbor>> = apiService.neighbors
    fun deleteNeighbor(neighbor: Neighbor) = apiService.deleteNeighbor(neighbor)
    fun updateFavoriteStatus(neighbor: Neighbor) = apiService.updateFavoriteStatus(neighbor)
    fun createNeighbor(neighbor: Neighbor) = apiService.createneighbor(neighbor)

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
