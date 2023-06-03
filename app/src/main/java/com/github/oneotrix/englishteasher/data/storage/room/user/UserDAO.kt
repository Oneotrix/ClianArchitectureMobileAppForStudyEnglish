package com.github.oneotrix.englishteasher.data.storage.room.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.oneotrix.englishteasher.data.storage.models.User

@Dao
interface UserDAO {

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmailAndPassword(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

}