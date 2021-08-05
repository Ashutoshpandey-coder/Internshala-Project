package com.example.internshala.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.internshala.R
import com.example.internshala.databinding.ItemDashboardBinding
import com.example.internshala.models.WorkshopModel
import com.example.internshala.utils.Constants

class DashboardListAdapter(
    private val context: Context,
    private val workshopList: ArrayList<WorkshopModel>
) : RecyclerView.Adapter<DashboardListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemDashboardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);
        return ViewHolder(ItemDashboardBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = workshopList[position]

        holder.binding.tvMode.text = model.mode
        holder.binding.ivWorkshopImage.setImageResource(model.image.toInt())
        holder.binding.tvTitle.text = model.title
        holder.binding.tvDescription.text = model.description

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
