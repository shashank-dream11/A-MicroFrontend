package com.shashank.matchcard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.shashank.modulecontracts.MatchCardModel
import kotlinx.android.synthetic.main.sample_match_card.view.*

class MatchCard : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        addView(LayoutInflater.from(context).inflate(R.layout.sample_match_card, this, false))
    }


    fun bindData(model : MatchCardModel) {
        txtContestName.text = model.TourName
        txtTeam1.text = model.Team1Name
        txtTeam2.text = model.Team2Name
        txtContest.text = model.contestsText
    }
}
