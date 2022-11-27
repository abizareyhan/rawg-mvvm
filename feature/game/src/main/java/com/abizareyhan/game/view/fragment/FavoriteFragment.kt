package com.abizareyhan.game.view.fragment


import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abizareyhan.core.base.BaseFragment
import com.abizareyhan.core.constant.BundleKey
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.game.adapter.GameAdapter
import com.abizareyhan.game.model.Game
import com.abizareyhan.game.viewmodel.FavoriteViewModel
import com.abizareyhan.playground.home.R
import com.abizareyhan.playground.home.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val gameAdapter: GameAdapter by lazy {
        GameAdapter(false, ::onGameClick)
    }

    override fun init() {
        setupRecyclerView()
        observeGames()

        favoriteViewModel.getFavorites()
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvFavorite.adapter = gameAdapter
            rvFavorite.layoutManager = LinearLayoutManager(root.context)
        }
    }

    private fun observeGames() {
        favoriteViewModel.getGamesListLiveData.observe(this) {
            when(it.status) {
                ResourceState.SUCCESS -> {
                    gameAdapter.updateItems(it.data)
                }
                ResourceState.FAILED -> Toast.makeText(binding.root.context, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onGameClick(game: Game) {
        findNavController().navigate(
            R.id.action_favoriteFragment_to_detailFragment,
            bundleOf(
                BundleKey.GAME to game
            )
        )
    }
}