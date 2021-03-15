package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myrecyclerview.databinding.ItemCardviewHeroBinding

class CardViewHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnclickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CardViewViewHolder(private val binding: ItemCardviewHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(hero.photo)
                        .apply(RequestOptions().override(350, 550))
                        .into(imgItemPhoto)

                tvItemName.text = hero.name
                tvItemDescription.text = hero.description

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(hero)
                }
                btnSetFavorite.setOnClickListener {
                    Toast.makeText(itemView.context, "Favorite ${hero.name}", Toast.LENGTH_SHORT).show()
                }
                btnSetShare.setOnClickListener {
                    Toast.makeText(itemView.context, "Share ${hero.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val binding = ItemCardviewHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, i: Int) {
        holder.bind(listHero[i])
    }

    override fun getItemCount(): Int = listHero.size

    interface OnItemClickCallback {
        fun onItemClicked(hero: Hero)
    }

}