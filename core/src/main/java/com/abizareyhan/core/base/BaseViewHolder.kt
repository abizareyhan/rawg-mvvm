package com.abizareyhan.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M: BaseModel>(
    open val binding: ViewBinding
): RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(model: M)
}