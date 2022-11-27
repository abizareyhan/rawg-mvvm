package com.abizareyhan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.abizareyhan.domain.model.AddedByStatusModel
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.GameDeveloperModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "favorite")
data class GameDetailEntity(
    @PrimaryKey override val id: Int,
    override val name: String? = null,
    override val released: String? = null,
    override val tba: Boolean? = null,
    override val backgroundImage: String? = null,
    override val rating: Double? = null,
    @TypeConverters(AddedByStatusModelConverter::class) override val addedByStatus: AddedByStatusModel? = null,
    override val description: String? = null,
    @TypeConverters(ListGameDeveloperModelConverter::class) override val developers: List<GameDeveloperModel>? = null
): GameDetailModel()

class AddedByStatusModelConverter {
    @TypeConverter
    fun toModel(json: String?): AddedByStatusModel =
        Gson().fromJson(json, object : TypeToken<AddedByStatusModel>() {}.type) ?: AddedByStatusModel()

    @TypeConverter
    fun toJson(model: AddedByStatusModel?) =
        Gson().toJson(model, object : TypeToken<AddedByStatusModel>() {}.type) ?: ""
}


class ListGameDeveloperModelConverter {
    @TypeConverter
    fun toModel(json: String?): List<GameDeveloperModel> =
        Gson().fromJson(json, object : TypeToken<List<GameDeveloperModel>>() {}.type) ?: listOf()

    @TypeConverter
    fun toJson(model: List<GameDeveloperModel>?) =
        Gson().toJson(model, object : TypeToken<List<GameDeveloperModel>>() {}.type) ?: ""
}
