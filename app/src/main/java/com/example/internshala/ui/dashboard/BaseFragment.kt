package com.example.internshala.ui.dashboard

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.internshala.R

//This fragment is for extra things like progress bar , we can use it
//by just extending this fragment.
open class BaseFragment : Fragment() {

    private lateinit var mProgressDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return null
    }

    fun showProgressDialog(message : String){
        mProgressDialog = Dialog(requireActivity())
        mProgressDialog.setContentView(R.layout.progress_dialog)
        mProgressDialog.findViewById<TextView>(R.id.tv_progress_message).text = message
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }
    fun hideProgressDialog(){
        mProgressDialog.hide()
    }
    fun dismissProgressDialog(){
        mProgressDialog.dismiss()
    }
}