package com.example.easylists.core_model.modules

import android.content.Context
import androidx.room.Room
import com.example.easylists.core_model.db_components.EasyListDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    private const val dbName = "easy_list_db"

    @Singleton
    @Provides
    fun providesDb(@ApplicationContext context : Context) : EasyListDataBase {
        return Room.databaseBuilder(context, EasyListDataBase::class.java, dbName).build()
    }

    @Singleton
    @Provides
    fun provideShopListDao(db : EasyListDataBase) = db.getShopListDao()

    @Singleton
    @Provides
    fun provideItemListDao(db : EasyListDataBase) = db.getItemListDao()

    @Singleton
    @Provides
    fun provideShopMultiItemListDao(db : EasyListDataBase) = db.getShopMultiItemListDao()

    @Singleton
    @Provides
    fun provideTodoListDao(db : EasyListDataBase) = db.getTodoListDao()
}

