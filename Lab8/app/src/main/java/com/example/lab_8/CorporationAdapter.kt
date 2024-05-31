package com.example.lab_8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab_8.databinding.RecycleViewBinding


class CorporationAdapter (private val items: List<Pair<String, String?>>,
                          private val onItemClick: (position: Int) -> Unit):
                    RecyclerView.Adapter<CorporationAdapter.CorporationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorporationHolder {
        val binding = RecycleViewBinding.inflate(LayoutInflater.from(parent.context))
        return CorporationHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CorporationHolder, position: Int) {
        val corporation = items[position]
        holder.bind(corporation.first, corporation.second)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CorporationHolder(private val binding: RecycleViewBinding,
                            private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.corporationButton.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }

            fun bind(corporationName: String, imageUrl: String?) {
                Glide.with(binding.imageView)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(binding.imageView.drawable)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_foreground)
                    .into(binding.imageView)
                binding.corporationButton.text = corporationName
        }
    }
}