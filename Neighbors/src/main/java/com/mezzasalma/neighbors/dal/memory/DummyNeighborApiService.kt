package com.mezzasalma.neighbors.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mezzasalma.neighbors.dal.NeighborApiService
import com.mezzasalma.neighbors.models.Neighbor
import com.mezzasalma.neighbors.repositories.service.DUMMY_NeighborS

class DummyNeighborApiService : NeighborApiService {
    private val _neighbors = MutableLiveData<List<Neighbor>>()

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighbors

    init {
        _neighbors.value = DUMMY_NeighborS
    }

    override fun deleteNeighbor(neighbor: Neighbor) {
        DUMMY_NeighborS.remove(neighbor)
        _neighbors.value = DUMMY_NeighborS
    }

    override fun createneighbor(neighbor: Neighbor) {
//        neighbor.id = DUMMY_NeighborS.last().id + 1
        DUMMY_NeighborS.add(neighbor)
        _neighbors.value = DUMMY_NeighborS
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        neighbor.favorite = !neighbor.favorite
        _neighbors.value = DUMMY_NeighborS
    }

    override fun updateDataNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
