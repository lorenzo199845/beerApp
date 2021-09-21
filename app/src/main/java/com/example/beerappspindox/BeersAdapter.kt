package com.example.beerappspindox

import BeerResponseItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.beer_item.view.*

class BeersAdapter(private val listener : IBeerListener? = null) : RecyclerView.Adapter<BeersAdapter.BeersViewHolder>() {

    private val beerList: ArrayList<BeerResponseItem> = ArrayList()


    fun updateBeerList(beerList: List<BeerResponseItem>) {
        this.beerList.clear()
        this.beerList.addAll(beerList)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeersAdapter.BeersViewHolder {
        return BeersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beer_item,parent,false), listener)
    }

    override fun onBindViewHolder(holder: BeersAdapter.BeersViewHolder, position: Int) {
        holder.bind(beerList[position])
    }

    override fun getItemCount(): Int {
        return beerList.size
    }


    inner class BeersViewHolder(itemView : View, private var listener : IBeerListener?) : RecyclerView.ViewHolder(itemView) {

        fun bind(beerModelView : BeerResponseItem) {
            itemView.tvTitle.text = beerModelView.name
            itemView.al_degree_tv.text = if(beerModelView.abv!= null) beerModelView.abv.toString() + "%" else "N/A"
            itemView.tv_desc_product.text = beerModelView.description
            Glide.with(itemView)
                    .load(beerModelView.image_url)
                    .error(R.drawable.ic_launcher_background)
                    .into(itemView.detailProductImage)

            itemView.setOnClickListener {
                listener?.onBeerClicked(beerModelView)
            }
        }
    }


    interface IBeerListener {

        fun onBeerClicked(beerModelView : BeerResponseItem)
    }
}