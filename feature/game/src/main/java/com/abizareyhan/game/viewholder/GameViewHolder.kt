package com.abizareyhan.game.viewholder

import com.abizareyhan.game.base.GameEndlessScrollViewHolder
import com.abizareyhan.game.model.Game
import com.abizareyhan.playground.home.R
import com.abizareyhan.playground.home.databinding.ItemGameBinding
import com.bumptech.glide.Glide
import java.time.format.DateTimeFormatter

class GameViewHolder(
    private val itemBinding: ItemGameBinding,
    private val onGameClick: (Game) -> Unit
): GameEndlessScrollViewHolder(itemBinding) {
    override fun bind(model: Game) {
        with(itemBinding) {
            Glide.with(root.context)
                .load(model.backgroundImage)
                .into(ivThumbnail)

            tvName.text = model.name
            tvReleaseDate.text = if(model.tba) {
                root.context.getString(
                    R.string.release_date,
                    root.context.getString(
                        R.string.to_be_announced
                    )
                )
            } else {
                root.context.getString(
                    R.string.release_date,
                    model.released?.format(
                        DateTimeFormatter.ofPattern("d MMM y")
                    ) ?: root.context.getString(
                        R.string.to_be_announced
                    )
                )
            }
            tvRating.text = model.rating.toString()

            root.setOnClickListener {
                onGameClick(model)
            }
        }
    }
}