package com.munirderman.adapters

import android.view.View
import android.view.ViewGroup
import com.github.stephenvinouze.advancedrecyclerview.core.adapters.RecyclerAdapter


open class SampleAdapter : RecyclerAdapter<Sample>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View =
            SampleItemView(parent.context)

    override fun onBindItemView(view: View, position: Int) {
        when (view) {
            is SampleItemView -> view.bind(items[position], isItemViewToggled(position))
        }
    }
}
