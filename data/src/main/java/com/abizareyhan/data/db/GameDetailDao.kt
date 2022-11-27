package com.abizareyhan.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abizareyhan.data.model.GameDetailEntity

@Dao
interface GameDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(game: GameDetailEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun removeFavoriteGame(id: Int)

    @Query("SELECT * FROM favorite")
    suspend fun getFavoriteGames(): List<GameDetailEntity>

    @Query("SELECT *FROM favorite WHERE id = :id ")
    suspend fun getFavoriteGameByID(id: Int): List<GameDetailEntity>

}