package com.example.tastytour.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastytour.activities.RestaurantActivity
import com.example.tastytour.databinding.FragmentSavedBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        binding.savedIM.setOnClickListener {
            val intent = Intent(requireContext(), RestaurantActivity::class.java)
            startActivity(intent)

        }
        return binding.root


    }

}