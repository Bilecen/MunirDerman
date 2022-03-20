package com.munirderman.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.munirderman.R
import kotlinx.android.synthetic.main.view_pdf_item.view.*


class SampleItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_pdf_item, this, true)
    }

    fun bind(sample: Sample, isToggled: Boolean) {
//        pdfItemImage.text = String.format("%d", sample.id)
        pdfItemNameTV.text = sample.name
        pdfItemTickIcon.visibility = View.GONE
        if (isToggled) {

//            PdfShow(sample.name)
        }
    }


}
