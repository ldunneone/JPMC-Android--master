package com.luke.codejpmc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.luke.codejpmc.database.DatabaseAlbum
import com.luke.codejpmc.database.AlbumDao
import com.luke.codejpmc.database.AlbumDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AlbumDatabase
    private lateinit var dao: AlbumDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AlbumDatabase::class.java
        ).allowMainThreadQueries().build()
        dao =database.albums
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAlbum() = runBlockingTest{
        val albumItem = DatabaseAlbum(1, 10,"Sgt. Pepper's Lonely Heart Club's Band")

        dao.insertAll(listOf(albumItem))

        val allJoke = dao.getLocalDBAlbums().getOrAwaitValue()

        assertThat(allJoke).contains(albumItem)
    }
}