package com.abizareyhan.game.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.abizareyhan.core.base.BaseDiffUtilModel
import com.abizareyhan.core.extension.parseToLocalDate
import com.abizareyhan.domain.model.GameDetailModel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Keep
@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val released: LocalDate? = null,
    val tba: Boolean,
    val backgroundImage: String,
    val rating: Double,
    val totalPlayed: Int,
    val developers: List<String>,
    val description: String
): BaseDiffUtilModel, Parcelable {
    constructor(gameDetailModel: GameDetailModel): this(
        id = gameDetailModel.id ?: -1,
        name = gameDetailModel.name.orEmpty(),
        released = gameDetailModel.released?.parseToLocalDate(),
        tba = gameDetailModel.tba ?: false,
        backgroundImage = gameDetailModel.backgroundImage.orEmpty(),
        rating = gameDetailModel.rating ?: 0.0,
        totalPlayed = gameDetailModel.addedByStatus?.beaten ?: 0,
        developers = gameDetailModel.developers?.map { gameDeveloperEntity ->
            gameDeveloperEntity.name.orEmpty()
        } ?: listOf(),
        description = gameDetailModel.description.orEmpty()
    )

    override fun areItemsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is Game) {
            this.id == toCompare.id
        } else {
            super.areItemsTheSame(null)
        }
    }

    override fun areContentsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is Game) {
            val isSame = this.id == toCompare.id &&
                    this.name == toCompare.name &&
                    this.released == toCompare.released &&
                    this.tba == toCompare.tba &&
                    this.backgroundImage == toCompare.backgroundImage &&
                    this.rating == toCompare.rating &&
                    this.totalPlayed == toCompare.totalPlayed &&
                    this.developers == toCompare.developers &&
                    this.description == toCompare.description
            isSame
        } else {
            super.areContentsTheSame(null)
        }
    }
}