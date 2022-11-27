package com.abizareyhan.game.viewholder

import com.abizareyhan.game.base.GameEndlessScrollViewHolder
import com.abizareyhan.game.model.Game
import com.abizareyhan.playground.home.databinding.ItemLoadingBinding

class LoadingViewHolder(
    private val itemBinding: ItemLoadingBinding
): GameEndlessScrollViewHolder(itemBinding) {
    override fun bind(model: Game) {

    }
}