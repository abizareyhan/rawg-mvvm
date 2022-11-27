package com.abizareyhan.data.repository

import com.abizareyhan.data.service.RAWGApiService
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameDetailRequest
import com.abizareyhan.domain.model.request.GetGameListRequest
import com.abizareyhan.domain.repository.RAWGApiRepository
import javax.inject.Inject

class RAWGApiRepositoryImpl @Inject constructor(
    private val rawgApiService: RAWGApiService
): RAWGApiRepository {
    override suspend fun getGamesList(request: GetGameListRequest): Resource<List<GameDetailModel>> {
        val response = rawgApiService.getGamesList(
            page = request.page,
            pageSize = request.pageSize,
            search = request.search
        )

        return response.mapToResource {
            it.results
        }

    }

    override suspend fun getGameDetail(request: GetGameDetailRequest): Resource<GameDetailModel> {
        val response = rawgApiService.getGameDetail(
            id = request.id
        )

        return response.mapToResource {
            it
        }
    }

}