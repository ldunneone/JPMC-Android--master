package com.luke.codejpmc.DI


import com.luke.codejpmc.database.AlbumDatabase
import com.luke.codejpmc.network.ApiServices
import com.luke.codejpmc.repository.AlbumRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: ApiServices, dao: AlbumDatabase): AlbumRepository {
        return AlbumRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}