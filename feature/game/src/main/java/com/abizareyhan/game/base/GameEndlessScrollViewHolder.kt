package com.abizareyhan.game.base

import androidx.viewbinding.ViewBinding
import com.abizareyhan.core.base.BaseViewHolder
import com.abizareyhan.game.model.Game

abstract class GameEndlessScrollViewHolder(
    override val binding: ViewBinding
): BaseViewHolder<Game>(binding)