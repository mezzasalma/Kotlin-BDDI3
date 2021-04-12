package com.mezzasalma.neighbors.data.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mezzasalma.neighbors.models.Neighbor

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
        DUMMY_NeighborS.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        neighbor.favorite = !neighbor.favorite
        _neighbors.value = DUMMY_NeighborS
    }

    override fun updateDataNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
