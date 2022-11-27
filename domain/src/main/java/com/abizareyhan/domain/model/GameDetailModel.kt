package com.abizareyhan.domain.model

import androidx.annotation.Keep

@Keep
open class GameDetailModel(
    open val id: Int? = null,
    open val name: String? = null,
    open val released: String? = null,
    open val tba: Boolean? = null,
    open val backgroundImage: String? = null,
    open val rating: Double? = null,
    open val addedByStatus: AddedByStatusModel? = null,
    open val description: String? = null,
    open val developers: List<GameDeveloperModel>? = null,
)