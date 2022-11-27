package com.abizareyhan.data.service

import com.abizareyhan.data.BuildConfig
import com.abizareyhan.data.constant.APIEndpoint
import com.abizareyhan.data.model.response.GameDetailResponse
import com.abizareyhan.data.model.response.GamesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RAWGApiService {
    @GET(APIEndpoint.RAWG_API_GAMES_LIST)
    suspend fun getGamesList(
        @Query("key") key: String = BuildConfig.RAWG_API_KEY,
        @Query("page") page: Int?= null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("search") search: String? = null,
    ): GamesListResponse

    @GET(APIEndpoint.RAWG_API_GAME_DETAIL)
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.RAWG_API_KEY
    ): GameDetailResponse
}