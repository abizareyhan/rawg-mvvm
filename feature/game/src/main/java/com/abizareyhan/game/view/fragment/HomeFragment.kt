package com.abizareyhan.game.view.fragment

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abizareyhan.core.base.BaseFragment
import com.abizareyhan.core.constant.BundleKey
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.game.adapter.GameAdapter
import com.abizareyhan.game.model.Game
import com.abizareyhan.game.viewmodel.HomeViewModel
import com.abizareyhan.playground.home.R
import com.abizareyhan.playground.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val homeViewModel: HomeViewModel by viewModels()

    private val gameAdapter: GameAdapter by lazy {
        GameAdapter(true, ::onGameClick)
    }

    private var stopTypingJob: Job? = null

    override fun init() {
        setupSwipeRefreshLayout()
        setupRecyclerView()
        setupSearch()
        observeGames()

        if(homeViewModel.getGamesListLiveData.value?.data == null) {
            getGamesList()
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.currentPage = 1

            getGamesList(
                page = homeViewModel.currentPage,
                search = binding.inputSearch.text.toString()
            )
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvHome.adapter = gameAdapter
            rvHome.layoutManager = LinearLayoutManager(root.context)

            nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { view, _, scrollY, _, _ ->
                if (view.getChildAt(0).bottom <= nestedScrollView.height + scrollY) {
                    if (!swipeRefreshLayout.isRefreshing) {
                        homeViewModel.currentPage++
                        getGamesList(
                            page = homeViewModel.currentPage,
                            search = binding.inputSearch.text.toString()
                        )
                    }
                }
            })

        }
    }

    private fun setupSearch() {
        with(binding) {
            inputSearch.doAfterTextChanged {
                if(inputSearch.hasFocus()) {
                    stopTypingJob?.cancel()
                    stopTypingJob = lifecycleScope.launch(Dispatchers.IO) {
                        delay(1000)
                        launch(Dispatchers.Main) {
                            homeViewModel.currentPage = 1

                            getGamesList(
                                page = homeViewModel.currentPage,
                                search = binding.inputSearch.text.toString()
                            )
                        }
                    }
                    stopTypingJob?.start()
                }
            }
        }
    }

    private fun observeGames() {
        homeViewModel.getGamesListLiveData.observe(this) { resourceListGame ->
            hideLoading()

            when(resourceListGame.status) {
                ResourceState.SUCCESS -> {
                    val games = (homeViewModel.loadedGamesLiveData.value ?: listOf()).toMutableList()

                    if(games != resourceListGame.data) {
                        games.addAll(resourceListGame.data)
                        homeViewModel.loadedGamesLiveData.postValue(games)
                    }
                }
                ResourceState.FAILED -> {
                    Toast.makeText(binding.root.context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        homeViewModel.loadedGamesLiveData.observe(this) { games ->
            gameAdapter.updateItems(games)
        }
    }

    private fun getGamesList(
        page: Int? = null,
        pageSize: Int? = null,
        search: String? = null
    ) {
        showLoading()

        if(page == null || page == 1) {
            homeViewModel.loadedGamesLiveData.postValue(listOf())
        }

        homeViewModel.getGamesList(
            page, pageSize, search
        )
    }

    private fun hideLoading() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun onGameClick(game: Game) {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf(
                BundleKey.GAME to game
            )
        )
    }
}