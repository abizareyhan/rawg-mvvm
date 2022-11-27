package com.abizareyhan.game.view.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.abizareyhan.core.base.BaseFragment
import com.abizareyhan.core.constant.BundleKey
import com.abizareyhan.core.extension.loadInlineHTML
import com.abizareyhan.core.extension.parcelable
import com.abizareyhan.domain.base.ResourceState
import com.abizareyhan.game.model.Game
import com.abizareyhan.game.viewmodel.DetailViewModel
import com.abizareyhan.playground.home.R
import com.abizareyhan.playground.home.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var currentMenu: Menu

    override fun init() {

        setupActionBar()

        observeGame()

        arguments?.parcelable<Game>(BundleKey.GAME)?.also { game ->
            setGameData(game)
            detailViewModel.isGameFavorite(game)
            detailViewModel.getGameDetail(game.id)
        }
    }

    private fun setupActionBar() {
        (activity as? AppCompatActivity)?.also { appCompatActivity ->
            appCompatActivity.setSupportActionBar(binding.toolbar)
            appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            binding.toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_toolbar_menu, menu)
        currentMenu = menu
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.actionFavorite -> {
                if(menuItem.isChecked) {
                    detailViewModel.getGame()?.let { game ->
                        detailViewModel.deleteFavoriteGame(game)
                    }

                    menuItem.icon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_inactive)
                } else {
                    detailViewModel.insertFavoriteGame()
                    menuItem.icon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_active)
                }
            }
        }
        return true
    }

    private fun setGameData(game: Game) {
        with(binding) {
            Glide.with(root.context)
                .load(game.backgroundImage)
                .into(ivThumbnail)

            tvName.text = game.name
            tvReleaseDate.text = if(game.tba) {
                root.context.getString(
                    R.string.release_date,
                    root.context.getString(
                        R.string.to_be_announced
                    )
                )
            } else {
                root.context.getString(
                    R.string.release_date,
                    game.released?.format(
                        DateTimeFormatter.ofPattern("d MMM y")
                    ) ?: root.context.getString(
                        R.string.to_be_announced
                    )
                )
            }
            tvRating.text = game.rating.toString()
            tvPlayed.text = getString(R.string.played, game.totalPlayed)
            tvDeveloper.text = game.developers.joinToString(", ")
            wvDescription.loadInlineHTML(game.description)
        }
    }

    private fun observeGame() {
        detailViewModel.getGameDetailLiveData.observe(this) { resourceGame ->
            when(resourceGame.status) {
                ResourceState.SUCCESS -> {
                    setGameData(resourceGame.data)
                }
                ResourceState.FAILED -> {
                    Toast.makeText(binding.root.context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        detailViewModel.isGameFavoriteLiveData.observe(this) {
            when(it.status) {
                ResourceState.SUCCESS -> {
                    if(it.data) {
                        currentMenu.getItem(0).icon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_active)
                        currentMenu.getItem(0).isChecked = true
                    } else {
                        currentMenu.getItem(0).icon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_inactive)
                        currentMenu.getItem(0).isChecked = false
                    }
                }
                ResourceState.FAILED -> {
                    currentMenu.getItem(0).icon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_inactive)
                    currentMenu.getItem(0).isChecked = false
                }
            }
        }
    }
}