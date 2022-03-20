package com.munirderman.adapters

import android.content.Context
import com.munirderman.dbhelper.ButunSohbetler

/**
 * Created by Stephen Vinouze on 09/11/2015.
 */
data class Sample(val id: Int, val rate: Int, val name: String) {

    companion object {

        fun mockItems(items: ArrayList<String>): List<Sample> =
            (0 until items.count()).map {
                Sample(
                    id = it,
                    rate = (Math.random() * 5).toInt(),
                    name = items[it]
                )
            }

        fun mockItemsSql(items: ArrayList<ButunSohbetler>): List<ButunSohbetler> =
            (0 until items.count()).map {
                ButunSohbetler(
                    Id = it,
                    Title =items[it].Title,
                    VideoLink = items[it].VideoLink,
                    Description =items[it].Description
                )
            }

    }
}



