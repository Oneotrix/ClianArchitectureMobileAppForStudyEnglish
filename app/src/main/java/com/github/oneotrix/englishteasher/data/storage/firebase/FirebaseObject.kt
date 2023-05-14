package com.github.oneotrix.englishteasher.data.storage.firebase

import android.util.Log
import com.github.oneotrix.englishteasher.data.storage.models.RegistrationResult
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val USERS = "Users"
private const val TAG_USER_REGISTRATION = "USER_REGISTRATION"

object FirebaseObject: FirebaseUsage{

    private val database = Firebase.database
    private lateinit var mAuth: FirebaseAuth

    override fun createUsersDatabase() {
        database.getReference(USERS).push().setValue("")
    }

    override fun createUser(user: User): RegistrationResult {
        var result: RegistrationResult = RegistrationResult()
        mAuth = FirebaseAuth.getInstance()

        try {
            mAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        result = RegistrationResult(result = true, description = null)
                    } else {
                        result = RegistrationResult(result = false, description = it.exception?.message)

                        Log.i(TAG_USER_REGISTRATION, "Registration is not successful \n " +
                                "reason : ${it.exception?.message}")
                    }
                }
        } catch (e : Exception) {
            Log.i(TAG_USER_REGISTRATION, "Registration is not successful \n " +
                    "exeption : ${e.message}")
            result = RegistrationResult(result = false, description = e.message)
        }

        return result
    }

    override fun modifyUser(uid: String, user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(uid: String) {
        TODO("Not yet implemented")
    }

    override fun takeUserData(uid: String) {
        TODO("Not yet implemented")
    }


}