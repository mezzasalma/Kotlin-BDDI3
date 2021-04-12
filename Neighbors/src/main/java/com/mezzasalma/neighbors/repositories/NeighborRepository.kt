package com.mezzasalma.neighbors.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.mezzasalma.neighbors.dal.NeighborApiService
import com.mezzasalma.neighbors.dal.room.RoomNeighborApiService
import com.mezzasalma.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val apiService: NeighborApiService

    init {
        apiService = RoomNeighborApiService(application)
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbors(): LiveData<List<Neighbor>> = apiService.neighbors
    fun deleteNeighbor(neighbor: Neighbor) = apiService.deleteNeighbor(neighbor)
    fun updateFavoriteStatus(neighbor: Neighbor) = apiService.updateFavoriteStatus(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
