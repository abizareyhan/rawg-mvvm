package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameListRequest
import com.abizareyhan.domain.repository.RAWGApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetGamesListUseCase @Inject constructor(
    private val rawgApiRepository: RAWGApiRepository
): BaseUseCase<GetGameListRequest, List<GameDetailModel>>() {
    override fun invoke(parameter: GetGameListRequest) {
        launch {
            transformToUseCaseLiveData(
                rawgApiRepository.getGamesList(parameter)
            )
        }
    }
}