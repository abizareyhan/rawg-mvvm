package com.abizareyhan.game.adapter

import android.view.ViewGroup
import com.abizareyhan.core.base.BaseAdapter
import com.abizareyhan.core.extension.viewBinding
import com.abizareyhan.game.base.GameEndlessScrollViewHolder
import com.abizareyhan.game.model.Game
import com.abizareyhan.game.viewholder.GameViewHolder
import com.abizareyhan.game.viewholder.LoadingViewHolder
import com.abizareyhan.playground.home.databinding.ItemGameBinding
import com.abizareyhan.playground.home.databinding.ItemLoadingBinding

class GameAdapter(
    private val endlesssScrollEnabled: Boolean = false,
    private val onGameClick: (Game) -> Unit = { }
) : BaseAdapter<Game, GameEndlessScrollViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameEndlessScrollViewHolder {
        return when(viewType) {
            1 -> LoadingViewHolder(parent.viewBinding(ItemLoadingBinding::inflate))
            else -> GameViewHolder(parent.viewBinding(ItemGameBinding::inflate), onGameClick)
        }
    }

    override fun getItemCount(): Int {
        return if(endlesssScrollEnabled) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(endlesssScrollEnabled) {
            if(position == itemCount - 1) {
                1
            } else {
                super.getItemViewType(position)
            }
        } else {
            super.getItemViewType(position)
        }
    }
}