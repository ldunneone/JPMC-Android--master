package com.luke.codejpmc.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.luke.codejpmc.repository.AlbumRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class SyncDatabase (appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params),
    KoinComponent
{
    val albumRepository : AlbumRepository by inject()

    companion object {
        const val WORK_NAME = "com.luke.codejpmc.background.SyncDatabase"
    }

    override suspend fun doWork(): Result {
        try {
            albumRepository.refresh()
        } catch (e: Exception){
            return Result.retry()
        }
        return Result.success()
    }
}