package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertFavoriteGameUseCase @Inject constructor(
    private val repository: GameLocalDatabaseRepository
): BaseUseCase<GameDetailModel, Unit>() {

    override fun invoke(parameter: GameDetailModel) {
        launch {
            repository.insertNewFavoriteGame(parameter)
        }
    }
}