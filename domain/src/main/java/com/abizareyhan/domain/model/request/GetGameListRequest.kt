package com.abizareyhan.domain.model.request

import androidx.annotation.Keep

@Keep
data class GetGameListRequest(
    val page: Int? = null,
    val pageSize: Int? = null,
    val search: String? = null,
)