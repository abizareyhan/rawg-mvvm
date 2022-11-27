package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class IsGameFavoriteUseCase @Inject constructor(
    private val repository: GameLocalDatabaseRepository
): BaseUseCase<Int, Boolean>() {

    override fun invoke(parameter: Int) {
        launch {
            transformToUseCaseLiveData(
                repository.checkIfGameIsFavorite(parameter)
            )
        }
    }
}