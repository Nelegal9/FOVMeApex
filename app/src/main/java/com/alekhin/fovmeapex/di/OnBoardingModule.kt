package com.alekhin.fovmeapex.di

import android.content.Context
import com.alekhin.fovmeapex.onboarding.OnBoardingDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardingModule {

    @Provides
    @Singleton
    fun provideOnBoardingDataStore(@ApplicationContext context: Context) = OnBoardingDataStore(context)
}