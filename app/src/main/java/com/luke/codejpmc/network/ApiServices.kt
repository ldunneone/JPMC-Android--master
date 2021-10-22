package com.luke.codejpmc.network

import com.luke.codejpmc.database.DatabaseAlbum
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET(API_Calls.API_ALBUMS_LIST)
    fun getAlbum(): Deferred<List<DatabaseAlbum>>


    @GET("album/{albumId}")
    fun getAlbumDetails(@Path("albumId") albumId:Int): Response<DatabaseAlbum>
}