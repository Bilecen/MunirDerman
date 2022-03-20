package com.munirderman.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.munirderman.R
import com.munirderman.dbhelper.ButunSohbetler
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.view_sample_item.view.*


class ButunSohbetlerItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_sample_item, this, true)
    }

    fun bind(butunSohbetler: ButunSohbetler, isToggled: Boolean) {
//        sampleItemIndexTV.text = String.format("%d", butunSohbetler.Id)
        sampleItemNameTV.text = butunSohbetler.Title
        sampleItemTickIcon.visibility = View.GONE
        if (isToggled) {

//            PdfShow(sample.name)
        }
    }


}
