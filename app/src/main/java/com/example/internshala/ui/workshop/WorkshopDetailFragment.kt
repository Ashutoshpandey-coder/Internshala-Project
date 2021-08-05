package com.example.internshala.ui.workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.internshala.R
import com.example.internshala.database.DatabaseHandler
import com.example.internshala.databinding.FragmentWorkshopDetailBinding
import com.example.internshala.models.WorkshopModel
import com.example.internshala.utils.Constants


class WorkshopDetailFragment : Fragment() {

    private var workshopModel: WorkshopModel? = null
    private lateinit var binding: FragmentWorkshopDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db = DatabaseHandler(requireContext(), null)

        binding = FragmentWorkshopDetailBinding.inflate(inflater, container, false)
        val root = binding.root


        val bundle = this.arguments
        if (bundle != null) {
            workshopModel = bundle.getParcelable(Constants.WORKSHOP_MODEL)
        }

        if (workshopModel != null) {
            if(workshopModel!!.isApplied == 1){
                binding.btnApply.visibility = View.GONE
            binding.tvAlreadyApplied.visibility = View.VISIBLE
            }else{
                binding.btnApply.visibility = View.VISIBLE
                binding.tvAlreadyApplied.visibility = View.GONE
            }
            binding.tvTitle.text = workshopModel!!.title
            binding.tvDescription.text = workshopModel!!.description
            binding.tvMode.text = workshopModel!!.mode
            binding.ivWorkshopImage.setImageResource(workshopModel!!.image.toInt())
        }


        binding.btnApply.setOnClickListener {
                if (Constants.getCurrentUserId().isNotEmpty()) {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.fragment_workshop_detail_to_fragment_workshop)
                    Toast.makeText(
                        requireContext(),
                        "Successfully applied to " + workshopModel!!.title,
                        Toast.LENGTH_SHORT
                    ).show()
                    workshopModel!!.isApplied = 1
                    db.updateWorkshop(workshopModel!!)

                    binding.btnApply.visibility = View.GONE
                    binding.tvAlreadyApplied.visibility = View.VISIBLE


                } else {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.fragment_workshop_detail_to_fragment_profile)
                    Toast.makeText(requireContext(), "You need to Login first.", Toast.LENGTH_SHORT)
                        .show()
                }
        }


        // Inflate the layout for this fragment
        return root
    }
}