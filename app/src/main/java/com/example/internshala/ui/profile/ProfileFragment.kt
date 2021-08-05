package com.example.internshala.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.databinding.FragmentProfileBinding
import com.example.internshala.ui.dashboard.BaseFragment
import com.example.internshala.utils.Constants

class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).setActionBarTitle(getString(R.string.title_profile))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnSignUp.setOnClickListener {
            showProgressDialog(getString(R.string.please_wait))
            if (binding.btnSignUp.text.equals(getString(R.string.signup))) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.fragment_profile_to_fragment_sign_up)
            }else{
                Toast.makeText(requireContext(), "This feature is not implemented yet.", Toast.LENGTH_SHORT).show()
            }
            hideProgressDialog()
        }
        binding.tvLogIn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.fragment_profile_to_fragment_log_in)
        }
        
        if (Constants.getCurrentUserId().isNotEmpty()){
            val sharedPreference : SharedPreferences = requireContext().getSharedPreferences(Constants.USER_DETAIL_SHARED_PREFERENCE,
                Context.MODE_PRIVATE)
            binding.tvUserName.text = sharedPreference.getString(Constants.NAME,getString(R.string.text_guest))
            binding.tvUserEmail.text = sharedPreference.getString(Constants.EMAIL, getString(R.string.dummy_email_id))
            binding.tvUserNumber.text = sharedPreference.getString(Constants.PHONE,getString(R.string.dummy_phone_number))
            
            binding.tvLoggedInGuest.visibility = View.GONE

            binding.llHaveAccount.visibility = View.GONE
            
            binding.btnSignUp.text = getString(R.string.log_out_text)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}