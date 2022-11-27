package com.abizareyhan.data.model.response

import androidx.annotation.Keep
import com.abizareyhan.domain.model.GameDeveloperModel
import com.squareup.moshi.Json

@Keep
data class GameDeveloperResponse(
    @Json(name = "id") override val id: Int? = null,
    @Json(name = "name") override val name: String? = null
): GameDeveloperModel()