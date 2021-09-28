package com.luke.codejpmc

import android.app.Application
import com.luke.codejpmc.DI.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        /**
         * Start Koin
         */
        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.NONE)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule, databaseModule))
        }
    }
}