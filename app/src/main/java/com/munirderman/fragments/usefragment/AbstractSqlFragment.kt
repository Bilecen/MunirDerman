package com.munirderman.fragments.usefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.munirderman.R
import kotlinx.android.synthetic.main.fragment_pdfview.*

/**
 * Created by Stephen Vinouze on 09/11/2015.
 */
abstract class AbstractSqlFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sqlview, container, false)

}
