package com.mezzasalma.neighbors.data.service

import androidx.lifecycle.LiveData
import com.mezzasalma.neighbors.models.Neighbor

interface NeighborApiService {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbors: LiveData<List<Neighbor>>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbor(neighbor: Neighbor)

    /**
     * Create a neighbor
     * @param neighbor: Neighbor
     */
    fun createneighbor(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing neighbor"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing neighbor"
     * @param neighbor: Neighbor
     */
    fun updateDataNeighbor(neighbor: Neighbor)
}
