package com.mezzasalma.neighbors.adapters

import com.mezzasalma.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
    fun onUpdateFavoriteStatus(neighbor: Neighbor)
    fun openLink(neighbor: Neighbor)
}
