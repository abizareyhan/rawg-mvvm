package com.abizareyhan.data.repository

import com.abizareyhan.data.db.GameDetailDao
import com.abizareyhan.data.model.GameDetailEntity
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import javax.inject.Inject

class GameLocalDatabaseRepositoryImpl @Inject constructor(
    private val gameDetailDao: GameDetailDao
): GameLocalDatabaseRepository {
    override suspend fun getFavoriteList(): Resource<List<GameDetailModel>> {
        return try {
            Resource.success(
                gameDetailDao.getFavoriteGames()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.failed(e)
        }
    }

    override suspend fun insertNewFavoriteGame(gameDetailModel: GameDetailModel) {
        val gameDetailEntity = GameDetailEntity(
            id = gameDetailModel.id ?: -1,
            name = gameDetailModel.name,
            released = gameDetailModel.released,
            tba = gameDetailModel.tba ?: false,
            backgroundImage = gameDetailModel.backgroundImage,
            rating = gameDetailModel.rating ?: 0.0,
            addedByStatus = gameDetailModel.addedByStatus,
            developers = gameDetailModel.developers,
            description = gameDetailModel.description.orEmpty()
        )
        gameDetailDao.insertFavoriteGame(gameDetailEntity)
    }

    override suspend fun deleteFavoriteGame(id: Int) {
        gameDetailDao.removeFavoriteGame(id)
    }

    override suspend fun checkIfGameIsFavorite(id: Int): Resource<Boolean> {
        val isFavorite = gameDetailDao.getFavoriteGameByID(id).isNotEmpty()

        return Resource.success(isFavorite)
    }
}