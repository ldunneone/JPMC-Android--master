package com.luke.codejpmc.network

import com.luke.codejpmc.database.DatabaseAlbum
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiServices {
    @GET(API_Calls.API_ALBUMS_LIST)
    fun getAlbum(): Deferred<List<DatabaseAlbum>>
}