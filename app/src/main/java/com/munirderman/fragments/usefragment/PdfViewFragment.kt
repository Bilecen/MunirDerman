package com.munirderman.fragments.usefragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.munirderman.MainActivity
import com.munirderman.R
import com.munirderman.adapters.IOnBackPressed
import com.munirderman.adapters.Sample
import kotlinx.android.synthetic.main.fragment_pdfview.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PdfViewFragment() : AbstractPdfFragment(),IOnBackPressed{
    //    lateinit var viewprevious: Button
    private lateinit var sample: Sample

    constructor(sample: Sample) : this() {

        this.sample = sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as? AppCompatActivity)?.supportActionBar?.title = sample.name

        PdfShow(sample.name);

        viewprevious.setOnClickListener() {

            pagination(sample.id - 1)

        }
        viewnext.setOnClickListener() {

            pagination(sample.id + 1)

        }

    }

    fun pagination(count: Int) {
        var getSample =
            Sample.mockItems(PdfChoiceRecyclerFragment().getItemsAssets(requireContext()))
                .toMutableList()
        if (count >= 0 && count < getSample.count()) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, PdfViewFragment(getSample[count])).commit()
        } else {
            Toast.makeText(requireContext(), "Son sayfa!", Toast.LENGTH_SHORT).show()
        }

    }

    fun PdfShow(pdfName: String) {

        pdfView.fromAsset(pdfName)
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
            .password(null)
            .scrollHandle(DefaultScrollHandle(requireContext()))
            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
            .spacing(3)
            .invalidPageColor(Color.DKGRAY)
            .load();
    }


    override fun onBackPressed(): Boolean {

        requireActivity().title = getString(R.string.single_choice_recycler_name)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PdfChoiceRecyclerFragment()).commit()
        return false
//        val count: Int = requireActivity().supportFragmentManager.getBackStackEntryCount()
//        return if (count == 0) {
//            //action not popBackStack
//            true
//        } else {
//            requireActivity().supportFragmentManager.popBackStack()
//            false
//        }

    }


}



