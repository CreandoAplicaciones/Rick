package com.rick.and.morty.common.di

import android.content.Context
import com.rick.and.morty.common.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun  providePreferences(@ApplicationContext context: Context): Preferences {
        return Preferences(context)
    }

}