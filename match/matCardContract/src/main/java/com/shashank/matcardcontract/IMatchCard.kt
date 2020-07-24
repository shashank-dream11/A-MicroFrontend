package com.shashank.matcardcontract

import android.content.Context
import android.view.View
import com.shashank.prelude.IModuleContract

interface IMatchCard : IModuleContract {

    fun getMatchCardView(context: Context): View

    fun bindData(view : View, model : MatchCardModel)
}

data class MatchCardModel(
    val tourId : Int,
    val TourName : String,
    val Team1Name : String,
    val Team2Name : String,
    val contestsText : String
)