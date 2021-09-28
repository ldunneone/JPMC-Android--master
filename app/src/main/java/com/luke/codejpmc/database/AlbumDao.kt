package com.luke.codejpmc.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM DatabaseAlbum ORDER BY title ASC")
    fun getLocalDBAlbums(): LiveData<List<DatabaseAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(album: List<DatabaseAlbum>)
}

@Database(entities=[DatabaseAlbum::class], version=1)
abstract class AlbumDatabase: RoomDatabase(){
    abstract val albums: AlbumDao
}