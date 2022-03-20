package com.munirderman.models

import com.munirderman.dbhelper.ButunSohbetler


data class ButunSohbetlerModel(
    var Id: Int,
    var Title: String,
    var Description: String,
    var VideoLink: String
) {

    companion object {


        fun mockItemsSql(items: ArrayList<ButunSohbetler>): List<ButunSohbetler> =
            (0 until items.count()).map {
                ButunSohbetler(
                    Id = it,
                    Title = items[it].Title,
                    VideoLink = items[it].VideoLink,
                    Description = items[it].Description
                )
            }

    }
}