package com.alekhin.fovmeapex.di

import com.alekhin.fovmeapex.authentication.AuthRepository
import com.alekhin.fovmeapex.authentication.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
}