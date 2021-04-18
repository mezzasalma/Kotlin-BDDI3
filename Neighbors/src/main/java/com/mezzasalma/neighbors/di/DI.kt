package com.mezzasalma.neighbors.di

import android.app.Application
import com.mezzasalma.neighbors.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
