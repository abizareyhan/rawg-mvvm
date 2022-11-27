package com.abizareyhan.domain.usecase

import com.abizareyhan.domain.base.BaseUseCase
import com.abizareyhan.domain.model.GameDetailModel
import com.abizareyhan.domain.model.request.GetGameDetailRequest
import com.abizareyhan.domain.repository.RAWGApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val rawgApiRepository: RAWGApiRepository
): BaseUseCase<GetGameDetailRequest, GameDetailModel>() {
    override fun invoke(parameter: GetGameDetailRequest) {
        launch {
            transformToUseCaseLiveData(
                rawgApiRepository.getGameDetail(parameter)
            )
        }
    }
}