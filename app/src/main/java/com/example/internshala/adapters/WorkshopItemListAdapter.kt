package com.example.internshala.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.databinding.ItemWorkshopBinding
import com.example.internshala.models.WorkshopModel
import com.example.internshala.utils.Constants

class WorkshopItemListAdapter(
    private val context: Context,
    private val workshopList: ArrayList<WorkshopModel>
) : RecyclerView.Adapter<WorkshopItemListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemWorkshopBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_workshop, parent, false);
        return ViewHolder(ItemWorkshopBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = workshopList[position]

        holder.binding.tvMode.text = model.mode
        holder.binding.ivWorkshopImage.setImageResource(model.image.toInt())
        holder.binding.tvTitle.text = model.title

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(Constants.WORKSHOP_MODEL,workshopList[position])
            Navigation.findNavController(holder.itemView).navigate(R.id.navigation_workshop_detail,bundle)
        }

    }

    override fun getItemCount(): Int {
        return workshopList.size
    }

}
