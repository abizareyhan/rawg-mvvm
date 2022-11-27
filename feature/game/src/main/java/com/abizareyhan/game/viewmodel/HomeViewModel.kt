package com.abizareyhan.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abizareyhan.core.extension.parseToLocalDate
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.domain.model.request.GetGameListRequest
import com.abizareyhan.domain.usecase.GetGamesListUseCase
import com.abizareyhan.game.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesListUseCase: GetGamesListUseCase
): ViewModel() {
    var currentPage = 1

    val loadedGamesLiveData: MutableLiveData<List<Game>> = MutableLiveData()

    val getGamesListLiveData: LiveData<Resource<List<Game>>> = Transformations.switchMap(
        getGamesListUseCase.getResultLiveData()
    ) { resourceListGameDetailEntity ->
        val liveData = MutableLiveData<Resource<List<Game>>>()

        when(resourceListGameDetailEntity.status) {
            ResourceState.SUCCESS -> {
                val game = resourceListGameDetailEntity.data.map { gameDetailModel ->
                    Game(gameDetailModel)
                }

                liveData.postValue(Resource.success(game))
            }
            ResourceState.FAILED -> {
                liveData.postValue(resourceListGameDetailEntity.convertError())
            }
        }

        liveData
    }

    fun getGamesList(
        page: Int? = null,
        pageSize: Int? = null,
        search: String? = null
    ) {
        getGamesListUseCase(
            GetGameListRequest(
                page = page,
                pageSize = pageSize,
                search = search
            )
        )
    }
}