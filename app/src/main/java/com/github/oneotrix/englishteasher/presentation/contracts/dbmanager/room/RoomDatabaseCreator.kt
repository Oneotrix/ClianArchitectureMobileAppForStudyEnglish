package com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room

import android.content.Context
import androidx.room.Room
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.presentation.contracts.Constants

interface RoomDatabaseCreator {

    fun createUserDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = Constants.USER_DATABASE_NAME
        ).build()
    }

}