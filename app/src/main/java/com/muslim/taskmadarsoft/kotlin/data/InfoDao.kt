package com.muslim.taskmadarsoft.kotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InfoDao {

    @Insert
    fun insert(info: Info)

    @Update
    fun update(info: Info)

    @Delete
    fun delete(info: Info)

    @Query("DELETE FROM Info")
    fun deleteAllInfo()

    @Query("SELECT * FROM Info ORDER BY age DESC")
    fun getAllInfo(): LiveData<List<Info>>

}