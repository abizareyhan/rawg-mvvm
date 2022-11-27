package com.abizareyhan.domain.repository

import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameDetailRequest
import com.abizareyhan.domain.model.request.GetGameListRequest

interface GameLocalDatabaseRepository {
    suspend fun getFavoriteList(): Resource<List<GameDetailModel>>

    suspend fun insertNewFavoriteGame(gameDetailModel: GameDetailModel)

    suspend fun deleteFavoriteGame(id: Int)

    suspend fun checkIfGameIsFavorite(id: Int): Resource<Boolean>
}