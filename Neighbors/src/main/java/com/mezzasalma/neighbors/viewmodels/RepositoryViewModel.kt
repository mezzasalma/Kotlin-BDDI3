package com.mezzasalma.neighbors.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mezzasalma.neighbors.di.DI
import com.mezzasalma.neighbors.models.Neighbor
import com.mezzasalma.neighbors.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbors()
}
