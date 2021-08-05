package com.example.internshala.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.adapters.DashboardListAdapter
import com.example.internshala.database.DatabaseHandler
import com.example.internshala.databinding.FragmentDashboardBinding
import com.example.internshala.models.WorkshopModel
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).setActionBarTitle(getString(R.string.title_dashboard))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.btnApplyWorkshop.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.fragment_dashboard_to_fragment_workshop)
        }

        binding.shimmerViewContainer.visibility = View.VISIBLE
        binding.shimmerViewContainer.startShimmerAnimation()

        val db = DatabaseHandler(requireContext(),null)
        showAppliedWorkshopListInUI(db.getAppliedWorkshopList())

        binding.shimmerViewContainer.visibility = View.GONE
        binding.shimmerViewContainer.stopShimmerAnimation()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAppliedWorkshopListInUI(workshopList: ArrayList<WorkshopModel>) {
        if (workshopList.size > 0) {
            binding.rvAppliedWorkshop.visibility = View.VISIBLE
            binding.llNoWorkshopAppliedYet.visibility = View.GONE

            binding.rvAppliedWorkshop.layoutManager = LinearLayoutManager(requireActivity())
            binding.rvAppliedWorkshop.setHasFixedSize(true)

            val dashboardAdapter = DashboardListAdapter(requireActivity(), workshopList)
            binding.rvAppliedWorkshop.adapter = dashboardAdapter

        } else {
            binding.rvAppliedWorkshop.visibility = View.GONE
            binding.llNoWorkshopAppliedYet.visibility = View.VISIBLE
        }
    }
}


