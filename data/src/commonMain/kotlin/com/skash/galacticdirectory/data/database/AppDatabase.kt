package com.skash.galacticdirectory.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.skash.galacticdirectory.data.database.dao.CharacterDao
import com.skash.galacticdirectory.data.database.dao.RemoteKeysDao
import com.skash.galacticdirectory.data.database.entity.CharacterSpeciesCrossRef
import com.skash.galacticdirectory.data.database.entity.DetailedCharacterEntity
import com.skash.galacticdirectory.data.database.entity.PersonEntity
import com.skash.galacticdirectory.data.database.entity.PlanetEntity
import com.skash.galacticdirectory.data.database.entity.RemoteKeys
import com.skash.galacticdirectory.data.database.entity.SpeciesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        PersonEntity::class,
        RemoteKeys::class,
        DetailedCharacterEntity::class,
        PlanetEntity::class,
        SpeciesEntity::class,
        CharacterSpeciesCrossRef::class,
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPersonDao(): CharacterDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

    companion object
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal expect fun AppDatabase.Companion.createDatabaseBuilder(dbName: String): RoomDatabase.Builder<AppDatabase>

fun AppDatabase.Companion.getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase> = createDatabaseBuilder("galactic-directory")
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}