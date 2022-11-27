package com.abizareyhan.core.util

import androidx.recyclerview.widget.DiffUtil
import com.abizareyhan.core.base.BaseDiffUtilModel

class DiffUtilCallback(
    private val oldList: List<BaseDiffUtilModel>,
    private val newList: List<BaseDiffUtilModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: BaseDiffUtilModel? = oldList.getOrNull(oldItemPosition)
        val newItem: BaseDiffUtilModel? = newList.getOrNull(newItemPosition)

        return oldItem?.areItemsTheSame(newItem) ?: false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: BaseDiffUtilModel? = oldList.getOrNull(oldItemPosition)
        val newItem: BaseDiffUtilModel? = newList.getOrNull(newItemPosition)

        return oldItem?.areContentsTheSame(newItem) ?: false
    }
}