package com.abizareyhan.core.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.viewBinding(binding: (LayoutInflater, ViewGroup, Boolean) -> T): T {
    return binding(LayoutInflater.from(context), this, false)
}