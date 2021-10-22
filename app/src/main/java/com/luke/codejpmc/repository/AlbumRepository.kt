package com.luke.codejpmc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.luke.codejpmc.database.AlbumDatabase
import com.luke.codejpmc.database.DatabaseAlbum
import com.luke.codejpmc.database.asDomainModel
import com.luke.codejpmc.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AlbumRepository(private val ApiServices: ApiServices, private val database: AlbumDatabase) {
    suspend fun refresh(){
        // worker thread to perform API request and save data locally
        withContext(Dispatchers.IO){
            val albumList = ApiServices.getAlbum().await()
            database.albums.insertAll(albumList)
        }
    }


    suspend fun getAlbumDetails(albumId:Int):Results<DatabaseAlbum?>{
        // worker thread to perform API request and save data locally

            val responseWrapper  = ApiServices.getAlbumDetails(albumId)

        when {
            responseWrapper.isSuccessful -> {
               return Results.Ok(responseWrapper.body())
            }
            else -> {
                throw HttpException(responseWrapper)
            }
        }


    }

    val results: LiveData<List<DatabaseAlbum>> = Transformations.map(database.albums.getLocalDBAlbums()){
        it.asDomainModel()
    }
}