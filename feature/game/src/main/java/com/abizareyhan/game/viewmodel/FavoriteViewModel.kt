package com.abizareyhan.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abizareyhan.core.extension.parseToLocalDate
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.domain.usecase.GetFavoriteGamesListUseCase
import com.abizareyhan.game.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteGamesListUseCase: GetFavoriteGamesListUseCase
): ViewModel() {
    val getGamesListLiveData: LiveData<Resource<List<Game>>> = Transformations.switchMap(
        getFavoriteGamesListUseCase.getResultLiveData()
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

    fun getFavorites() {
        getFavoriteGamesListUseCase("")
    }
}