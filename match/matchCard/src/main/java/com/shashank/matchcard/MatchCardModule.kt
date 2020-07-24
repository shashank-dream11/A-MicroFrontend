package com.shashank.matchcard

import android.content.Context
import android.view.View
import com.shashank.matcardcontract.IMatchCard
import com.shashank.matcardcontract.MatchCardModel

object MatchCardModule : IMatchCard {

    override fun getMatchCardView(context: Context): View {
        return MatchCard(context)
    }

    override fun bindData(view: View, model: MatchCardModel) {
        if(view !is MatchCard)
            return
        view.bindData(model)
    }
}