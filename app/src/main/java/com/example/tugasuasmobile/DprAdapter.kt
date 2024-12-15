package com.example.tugasuasmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasuasmobile.databinding.ItemDprBinding
import com.bumptech.glide.Glide
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DprAdapter(var dprs:List<Dpr>) : RecyclerView.Adapter<DprAdapter.DprViewHolder>() {

    inner class DprViewHolder(private val binding: ItemDprBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var dprDao : DprDao
        lateinit var executorService : ExecutorService
        init {
            executorService = Executors.newSingleThreadExecutor()
            val dprDatabase = DprDatabase.getInstance(binding.root.context)
            dprDao = dprDatabase!!.dprDao()!!
        }
        fun favoriting(dpr: Dpr){
            executorService.execute(){
                val dprVal = dprDao.getDpr(dpr._id)
                println(dprVal.toString())
                if(dprVal==null){
                    dprDao.insert(dpr)
                } else{
                    dprDao.delete(dprVal)
                }
            }
            refresh(dpr._id)
        }


        fun bind(dpr: Dpr) {
            executorService.execute(){
                var dprVal = dprDao.getDpr(dpr._id)
                with(binding){
                    if(dprVal==null){
                        btnFavorite.setImageResource(R.drawable.ic_unfavorited)
                    } else{
                        btnFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
            }
            with(binding) {
                txtName.text = dpr.nama
                txtPartai.text = dpr.partai
                Glide.with(binding.root.context)
                    .load(dpr.fotoUrl)
                    .into(imgPhoto)
                btnFavorite.setOnClickListener {
                    Toast.makeText(binding.root.context, "kepencet", Toast.LENGTH_SHORT).show()
                    favoriting(dpr) }
            }
            refresh(dpr._id)
        }

        fun refresh(pkey: String){
            executorService.execute(){
                var dprVal = dprDao.getDpr(pkey)
                with(binding){
                    if(dprVal==null){
                        btnFavorite.setImageResource(R.drawable.ic_unfavorited)
                    } else{
                        btnFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DprViewHolder {
        val binding = ItemDprBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DprViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DprViewHolder, position: Int) {
        holder.bind(dprs[position])
    }

    override fun getItemCount(): Int = dprs.size


}

