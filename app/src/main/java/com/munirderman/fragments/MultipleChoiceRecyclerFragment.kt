package com.munirderman.adapters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.stephenvinouze.advancedrecyclerview.core.enums.ChoiceMode
import com.munirderman.fragments.usefragment.AbstractRecyclerFragment
import kotlinx.android.synthetic.main.recycler_layout.*

/**
 * Created by Stephen Vinouze on 06/11/2015.
 */
class MultipleChoiceRecyclerFragment : AbstractRecyclerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SampleAdapter().apply {
//            items = Sample.mockItems().toMutableList()
            choiceMode = ChoiceMode.MULTIPLE
            onClick = { _, position ->
                val sample = items[position]
                Toast.makeText(context, "Item clicked : ${sample.id} ($selectedItemViewCount selected)", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.adapter = adapter
    }
}
