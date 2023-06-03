package com.github.oneotrix.englishteasher.data.storage.room.user

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "login") val login: String
)