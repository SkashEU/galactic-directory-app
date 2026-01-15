package com.skash.galacticdirectory.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skash.galacticdirectory.data.database.entity.PersonEntity

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<PersonEntity>)

    @Query("SELECT * FROM people")
    fun pagingSource(): PagingSource<Int, PersonEntity>


    @Query("DELETE FROM people")
    suspend fun clear()

}