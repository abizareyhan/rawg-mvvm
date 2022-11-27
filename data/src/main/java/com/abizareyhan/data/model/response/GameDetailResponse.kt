package com.abizareyhan.data.model.response

import androidx.annotation.Keep
import com.abizareyhan.data.exception.ResourceNullMappingException
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.model.GameDetailModel
import com.squareup.moshi.Json

@Keep
data class GameDetailResponse(
    @Json(name = "id") override val id: Int? = null,
    @Json(name = "name") override val name: String? = null,
    @Json(name = "released") override val released: String? = null,
    @Json(name = "tba") override val tba: Boolean? = null,
    @Json(name = "background_image") override val backgroundImage: String? = null,
    @Json(name = "rating") override val rating: Double? = null,
    @Json(name = "added_by_status") override val addedByStatus: AddedByStatusResponse? = null,
    @Json(name = "description") override val description: String? = null,
    @Json(name = "developers") override val developers: List<GameDeveloperResponse>? = null,
    @Json(name = "error") val error: String? = null
): GameDetailModel() {
    fun <M> mapToResource(
        mapper: (GameDetailResponse) -> M?
    ): Resource<M> {
        return try {
            if(error == null) {
                mapper(this)?.let {
                    Resource.success(it)
                } ?: Resource.failed(ResourceNullMappingException())
            } else {
                Resource.failed(error, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.failed(e)
        }
    }
}