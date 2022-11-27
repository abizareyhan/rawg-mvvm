package com.abizareyhan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abizareyhan.data.model.AddedByStatusModelConverter
import com.abizareyhan.data.model.GameDetailEntity
import com.abizareyhan.data.model.ListGameDeveloperModelConverter

@Database(
    entities = [
        GameDetailEntity::class,
    ],
    version = 1
)
@TypeConverters(
    AddedByStatusModelConverter::class,
    ListGameDeveloperModelConverter::class,
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDetailDao(): GameDetailDao
}