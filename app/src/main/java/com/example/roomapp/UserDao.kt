package com.example.roomapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("Select * from user")
    suspend fun getAll(): List<User>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg user: User)
    @Insert
    suspend fun insertAll(vararg users: User)


}