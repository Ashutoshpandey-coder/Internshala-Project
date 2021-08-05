package com.example.internshala.ui.workshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.adapters.WorkshopItemListAdapter
import com.example.internshala.database.DatabaseHandler
import com.example.internshala.databinding.FragmentWorkshopBinding
import com.example.internshala.models.WorkshopModel

class WorkshopFragment : Fragment() {


    private var _binding: FragmentWorkshopBinding? = null
    private var db: DatabaseHandler? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).setActionBarTitle(getString(R.string.title_available_workshops))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWorkshopBinding.inflate(inflater, container, false)


        db = DatabaseHandler(requireContext(), null)
        if (db != null) {
            var workshopList: ArrayList<WorkshopModel> = ArrayList()
            workshopList = db!!.getWorkshopList()
            if (workshopList.size > 0) {
                showWorkshopListInUI(workshopList)
            } else {
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Awareness Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_one.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Student Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_two.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Professional Workshop",
                        getString(R.string.dummy_description),
                        "Offline",
                        R.drawable.ic_workshop_image_three.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Teaching Workshop",
                        getString(R.string.dummy_description),
                        "Offline",
                        R.drawable.ic_workshop_image_one.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Android Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_two.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Web Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_three.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Machine Learning Workshop",
                        getString(R.string.dummy_description),
                        "Offline",
                        R.drawable.ic_workshop_image_one.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Artificial Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_two.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Django Workshop",
                        getString(R.string.dummy_description),
                        "Offline",
                        R.drawable.ic_workshop_image_three.toString()
                    )
                )
                db!!.addWorkshop(
                    WorkshopModel(
                        0,
                        "Kotlin Workshop",
                        getString(R.string.dummy_description),
                        "Online",
                        R.drawable.ic_workshop_image_one.toString()
                    )
                )
                binding.shimmerViewContainer.visibility = View.VISIBLE
                binding.shimmerViewContainer.startShimmerAnimation()
                showWorkshopListInUI(db!!.getWorkshopList())

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showWorkshopListInUI(workshopList: ArrayList<WorkshopModel>) {
        if (workshopList.size > 0) {
            binding.rvWorkshopList.visibility = View.VISIBLE
            binding.tvNoWorkshopAvailable.visibility = View.GONE

            binding.rvWorkshopList.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvWorkshopList.setHasFixedSize(true)

            val workshopAdapter = WorkshopItemListAdapter(requireActivity(), workshopList)
            binding.rvWorkshopList.adapter = workshopAdapter

        } else {
            binding.rvWorkshopList.visibility = View.GONE
            binding.tvNoWorkshopAvailable.visibility = View.VISIBLE
        }
        binding.shimmerViewContainer.visibility = View.GONE
        binding.shimmerViewContainer.stopShimmerAnimation()
    }
}