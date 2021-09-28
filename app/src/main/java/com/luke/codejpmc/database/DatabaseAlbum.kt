package com.luke.codejpmc.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseAlbum (
    @PrimaryKey
    var id: Int,
    var userId: Int,
    var title: String
)
fun List<DatabaseAlbum>.asDomainModel():List<DatabaseAlbum> {
    return map {
        DatabaseAlbum(
            id = it.id,
            userId = it.userId,
            title = it.title
        )
    }
}