package by.zharikov.cocktails.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.zharikov.cocktails.databinding.ItemHomeBinding

class HomeAdapter(private val homeList: List<HomeList>, context: Context) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val clickHandler: ClickEventHandler = context as ClickEventHandler

    inner class HomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(home: HomeList, position: Int) {
            with(binding) {
                textHome.text = home.categoryName
                imageCategory.setImageResource(home.image)
                binding.root.setOnClickListener {
                    clickHandler.viewNextFragment(it, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeList[position], position)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }
}

interface ClickEventHandler {
    fun viewNextFragment(holder: View, position: Int)
}