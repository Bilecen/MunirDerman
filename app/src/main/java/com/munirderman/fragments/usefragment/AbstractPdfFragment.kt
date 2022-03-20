package com.munirderman.fragments.usefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.munirderman.R
import kotlinx.android.synthetic.main.fragment_pdfview.*

/**
 * Created by Stephen Vinouze on 09/11/2015.
 */
abstract class AbstractPdfFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_pdfview, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        pdfView.run {
//
//        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


}
