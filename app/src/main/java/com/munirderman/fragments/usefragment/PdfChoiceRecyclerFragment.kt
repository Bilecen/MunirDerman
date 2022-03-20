package com.munirderman.fragments.usefragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.stephenvinouze.advancedrecyclerview.core.enums.ChoiceMode
import com.munirderman.R
import com.munirderman.adapters.Sample
import com.munirderman.adapters.SampleAdapter
import kotlinx.android.synthetic.main.recycler_layout.*


class PdfChoiceRecyclerFragment : AbstractRecyclerFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.single_choice_recycler_name)
        val adapter = SampleAdapter().apply {
            choiceMode = ChoiceMode.SINGLE

            items = Sample.mockItems(getItemsAssets(requireContext())).toMutableList()


            onClick = { _, position ->
                var sample = items[position]

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, PdfViewFragment(sample))
                    .commit()

//                Toast.makeText(
//                    context,
//                    "Item clicked : ${sample.id} ($selectedItemViewCount selected)",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }

        recyclerView.adapter = adapter
    }




    fun getItemsAssets(context: Context): ArrayList<String> {
        val items = ArrayList<String>()
        val assetManager = context.assets
        for (file in assetManager.list("")!!) {
            if (file.endsWith(".pdf")) items.add(file)
        }
        return items
    }
}
