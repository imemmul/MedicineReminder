package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.databinding.FragmentHomePageBinding
import com.example.project.drugs.adapter.DrugAdapter
import com.example.project.drugs.viewModel.DrugViewModel

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: DrugViewModel by activityViewModels()
    private lateinit var rvAdapter : DrugAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        binding.fabAddDrug.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_editDrugFragment)
        }

        rvAdapter = DrugAdapter()
        viewModel.drugs.observe(viewLifecycleOwner) { drugs ->
            rvAdapter.data = viewModel.drugs.value!!
        }
        val recyclerView =   binding.recyclerView
        println(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter= rvAdapter

        return binding.root

    }


    override fun onStart() {
        super.onStart()
        viewModel.retriveDrugs()
    }
}




