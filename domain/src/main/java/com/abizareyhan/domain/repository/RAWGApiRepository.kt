package com.abizareyhan.domain.repository

import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameDetailRequest
import com.abizareyhan.domain.model.request.GetGameListRequest

interface RAWGApiRepository {
    suspend fun getGamesList(
        request: GetGameListRequest
    ): Resource<List<GameDetailModel>>

    suspend fun getGameDetail(
        request: GetGameDetailRequest
    ): Resource<GameDetailModel>
}