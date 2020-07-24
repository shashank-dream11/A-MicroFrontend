package com.shashank.matchcenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.shashank.matcardcontract.MatchCardModel
import kotlinx.android.synthetic.main.fragment_home.*

class MatchCenterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myMatches.adapter = MyViewPagerAdapter(matchData)

        rvItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvItems.adapter = RecyclerViewAdapter(matchData)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchCenterFragment()
    }


}

class MyViewPagerAdapter(val matches: List<MatchCardModel>) : PagerAdapter() {

    override fun isViewFromObject(view: View, item: Any): Boolean {
        return item is View && view == item
    }

    override fun getCount() = matches.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return ModuleFactory.matchCardModule.getMatchCardView(container.context).apply {
            ModuleFactory.matchCardModule.bindData(this, matches[position])
        }
    }


}

class RecyclerViewAdapter(val matches: List<MatchCardModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RViewHolder>() {

    class RViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
        return RViewHolder(ModuleFactory.matchCardModule.getMatchCardView(parent.context));
    }

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RViewHolder, position: Int) {
        ModuleFactory.matchCardModule.bindData(holder.view, matches[position])
    }
}

val matchData = arrayListOf<MatchCardModel>(
    MatchCardModel(1, "IPL 2020", "Mumbai Indians", "Chennai Super Kings ", " 2 Team   2 Contests"),
    MatchCardModel(
        1,
        "IPL 2020",
        "Royal Challengers Bangalore",
        "Kings XI Punjab",
        " 3 Team   1 Contests"
    ),
    MatchCardModel(1, "IPL 2020", "Mumbai Indians", "Chennai Super Kings ", " 2 Team   2 Contests"),
    MatchCardModel(
        1,
        "IPL 2020",
        "Royal Challengers Bangalore",
        "Kings XI Punjab",
        " 3 Team   1 Contests"
    ),
    MatchCardModel(1, "IPL 2020", "Mumbai Indians", "Chennai Super Kings ", " 2 Team   2 Contests"),
    MatchCardModel(
        1,
        "IPL 2020",
        "Royal Challengers Bangalore",
        "Kings XI Punjab",
        " 3 Team   1 Contests"
    )
)