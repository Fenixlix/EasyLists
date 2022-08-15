package com.example.easylists.model.modules

import android.content.Context
import androidx.room.Room
import com.example.easylists.model.db_components.EasyListDataBase
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
    fun provideTodoDao(db : EasyListDataBase) = db.getTodoListDao()
}

