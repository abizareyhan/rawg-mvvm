package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameListRequest
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import com.abizareyhan.domain.repository.RAWGApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetFavoriteGamesListUseCase @Inject constructor(
    private val repository: GameLocalDatabaseRepository
): BaseUseCase<String?, List<GameDetailModel>>() {
    override fun invoke(parameter: String?) {
        launch {
            transformToUseCaseLiveData(
                repository.getFavoriteList()
            )
        }
    }
}