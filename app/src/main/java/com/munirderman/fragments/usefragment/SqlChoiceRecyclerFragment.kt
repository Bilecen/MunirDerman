package com.munirderman.fragments.usefragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.stephenvinouze.advancedrecyclerview.core.enums.ChoiceMode
import com.munirderman.R
import com.munirderman.adapters.ButunSohbetlerAdapter
import com.munirderman.dbhelper.ButunSohbetler
import com.munirderman.dbhelper.DbHelper
import com.munirderman.models.ButunSohbetlerModel
import kotlinx.android.synthetic.main.recycler_layout.*


class SqlChoiceRecyclerFragment : AbstractRecyclerFragment() {

    val db by lazy { DbHelper(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title =
            getString(R.string.sqltext_choice_recycler_name)

        val adapter = ButunSohbetlerAdapter().apply {
            choiceMode = ChoiceMode.SINGLE

            items =
                ButunSohbetlerModel.mockItemsSql(getItemsAssets()).toMutableList()
            onClick = { _, position ->
                val butunSohbetler = items[position]
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, SqlViewFragment(butunSohbetler,items)).commit()

//                Toast.makeText(
//                    context,
//                    "Item clicked : ${butunSohbetler.Id} ($selectedItemViewCount selected)",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }

        recyclerView.adapter = adapter
    }

    fun getItemsAssets(): ArrayList<ButunSohbetler> {
        var items = ArrayList<ButunSohbetler>()
        val dbManager = db.readData()
        for (info in dbManager) {
            items.add(info)
        }

        return items
    }
}
