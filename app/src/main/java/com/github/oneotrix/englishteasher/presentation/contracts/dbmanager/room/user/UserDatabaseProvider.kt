package com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user

import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.presentation.contracts.Constants


fun Fragment.userDbEntity() : UserDatabaseProvider {
    return requireActivity() as UserDatabaseProvider
}


interface UserDatabaseProvider  {

    fun getUserDatabase() : AppDatabase

}