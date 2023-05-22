package com.alekhin.fovmeapex.authentication

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun loginUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun registerUser(username: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}