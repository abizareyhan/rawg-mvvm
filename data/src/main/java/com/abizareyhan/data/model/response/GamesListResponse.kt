package com.abizareyhan.data.model.response

import androidx.annotation.Keep
import com.abizareyhan.data.exception.ResourceNullMappingException
import com.abizareyhan.domain.base.Resource
import com.squareup.moshi.Json

@Keep
data class GamesListResponse(
    @Json(name = "count") val count: Int? = null,
    @Json(name = "next") val next: String? = null,
    @Json(name = "previous") val previous: String? = null,
    @Json(name = "results") val results: List<GameDetailResponse>? = null,
    @Json(name = "error") val error: String? = null,
) {
    fun <M> mapToResource(
        mapper: (GamesListResponse) -> M?
    ): Resource<M> {
        return try {
            if(error == null || results?.isNotEmpty() == true) {
                results?.let {
                    mapper(this)?.let {
                        Resource.success(it)
                    } ?: Resource.failed(ResourceNullMappingException())
                } ?: Resource.failed(ResourceNullMappingException())
            } else {
                Resource.failed(error, ResourceNullMappingException())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.failed(e)
        }
    }
}