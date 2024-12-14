package com.example.tugasuasmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan9.databinding.ItemDprBinding
import com.bumptech.glide.Glide

class DprAdapter(private val onFavoriteClick: (Dpr) -> Unit) : RecyclerView.Adapter<DprAdapter.DprViewHolder>() {
    private val data = mutableListOf<Dpr>()

    inner class DprViewHolder(private val binding: ItemDprBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dpr: Dpr) {
            with(binding) {
                txtName.text = dpr.name
                Glide.with(itemView).load(dpr.photo).into(imgPhoto)
                btnFavorite.setOnClickListener { onFavoriteClick(dpr) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DprViewHolder {
        val binding = ItemDprBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DprViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DprViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<Dpr>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}

