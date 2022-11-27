package com.abizareyhan.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abizareyhan.core.extension.parseToLocalDate
import com.abizareyhan.domain.base.Resource
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.domain.model.request.GetGameDetailRequest
import com.abizareyhan.domain.usecase.DeleteFavoriteGameUseCase
import com.abizareyhan.domain.usecase.GetGameDetailUseCase
import com.abizareyhan.domain.usecase.InsertFavoriteGameUseCase
import com.abizareyhan.domain.usecase.IsGameFavoriteUseCase
import com.abizareyhan.game.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getGameDetailUseCase: GetGameDetailUseCase,
    private val insertFavoriteGameUseCase: InsertFavoriteGameUseCase,
    private val deleteFavoriteGameUseCase: DeleteFavoriteGameUseCase,
    private val isGameFavoriteUseCase: IsGameFavoriteUseCase
): ViewModel() {
    val getGameDetailLiveData: LiveData<Resource<Game>> = Transformations.switchMap(
        getGameDetailUseCase.getResultLiveData()
    ) { resourceGameEntity ->
        val liveData = MutableLiveData<Resource<Game>>()

        when(resourceGameEntity.status) {
            ResourceState.SUCCESS -> {
                val game = Game(resourceGameEntity.data)

                liveData.postValue(Resource.success(game))
            }
            ResourceState.FAILED -> {
                liveData.postValue(resourceGameEntity.convertError())
            }
        }

        liveData
    }

    fun getGameDetail(
        id: Int
    ) {
        getGameDetailUseCase(
            GetGameDetailRequest(
                id = id
            )
        )
    }

    fun getGame(): Game? {
        return getGameDetailLiveData.value?.data
    }

    fun insertFavoriteGame() {
        getGameDetailUseCase.getResultLiveData().value?.data?.let {
            insertFavoriteGameUseCase(
                it
            )
        }

    }

    fun deleteFavoriteGame(
        game: Game
    ) {
        deleteFavoriteGameUseCase(game.id)
    }

    val isGameFavoriteLiveData = isGameFavoriteUseCase.getResultLiveData()

    fun isGameFavorite(
        game: Game
    ) {
        isGameFavoriteUseCase(game.id)
    }
}