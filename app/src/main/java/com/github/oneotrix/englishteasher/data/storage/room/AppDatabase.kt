package com.github.oneotrix.englishteasher.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.oneotrix.englishteasher.data.storage.room.user.UserDAO
import com.github.oneotrix.englishteasher.data.storage.room.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
}