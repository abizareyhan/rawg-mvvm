package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteFavoriteGameUseCase @Inject constructor(
    private val repository: GameLocalDatabaseRepository
): BaseUseCase<Int, Unit>() {

    override fun invoke(parameter: Int) {
        launch {
            repository.deleteFavoriteGame(parameter)
        }
    }
}