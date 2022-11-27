package com.abizareyhan.data.model.response

import androidx.annotation.Keep
import com.abizareyhan.domain.model.AddedByStatusModel
import com.squareup.moshi.Json

@Keep
data class AddedByStatusResponse(
    @Json(name = "yet") override val yet: Int? = null,
    @Json(name = "owned") override val owned: Int? = null,
    @Json(name = "beaten") override val beaten: Int? = null,
    @Json(name = "toplay") override val toplay: Int? = null,
    @Json(name = "dropped") override val dropped: Int? = null,
    @Json(name = "playing") override val playing: Int? = null
): AddedByStatusModel()