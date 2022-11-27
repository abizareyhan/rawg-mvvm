package com.abizareyhan.domain.model

import androidx.annotation.Keep

@Keep
open class AddedByStatusModel(
    open val yet: Int? = null,
    open val owned: Int? = null,
    open val beaten: Int? = null,
    open val toplay: Int? = null,
    open val dropped: Int? = null,
    open val playing: Int? = null,
)