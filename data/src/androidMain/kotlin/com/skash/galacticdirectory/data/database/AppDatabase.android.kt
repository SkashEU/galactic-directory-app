package com.skash.galacticdirectory.data.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.startup.Initializer

internal object ApplicationContextHolder {
    lateinit var application: Application
        internal set

    fun isInitialized(): Boolean = ::application.isInitialized
}

class ApplicationContextInitializer : Initializer<Application> {
    override fun create(context: Context): Application {
        val app = context.applicationContext as Application
        ApplicationContextHolder.application = app
        return app
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}

internal actual fun AppDatabase.Companion.createDatabaseBuilder(dbName: String): RoomDatabase.Builder<AppDatabase> {
    val appContext = ApplicationContextHolder.application.applicationContext
    val dbFile = appContext.getDatabasePath("$dbName.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}