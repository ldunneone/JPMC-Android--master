package com.luke.codejpmc.ui.main

import com.luke.codejpmc.database.AlbumDatabase

sealed class AlbumDetailsEvent{
    data class AlbumdetailLoaded(val data:AlbumDatabase):AlbumDetailsEvent()
    object Loading:AlbumDetailsEvent()
    object AlbumError:AlbumDetailsEvent()
}
