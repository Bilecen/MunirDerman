package com.munirderman.adapters

import android.view.View
import android.view.ViewGroup
import com.github.stephenvinouze.advancedrecyclerview.core.adapters.RecyclerAdapter
import com.munirderman.dbhelper.ButunSohbetler


open class ButunSohbetlerAdapter : RecyclerAdapter<ButunSohbetler>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View =
            ButunSohbetlerItemView(parent.context)

    override fun onBindItemView(view: View, position: Int) {
        when (view) {
            is ButunSohbetlerItemView -> view.bind(items[position], isItemViewToggled(position))
        }
    }
}
