package com.studiomk.randomcatfacts.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.studiomk.randomcatfacts.R
import com.studiomk.randomcatfacts.databinding.FragmentFactsBinding
import com.studiomk.randomcatfacts.databinding.FragmentHomeBinding
import com.studiomk.randomcatfacts.presentation.viewModel.CatFactsViewModel
import com.studiomk.randomcatfacts.presentation.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_facts.*

class FactsFragment : Fragment() {

    private lateinit var factsViewModel: CatFactsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentFactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_facts, container, false)
        val viewModel = ViewModelProviders.of(this).get(CatFactsViewModel::class.java)
        binding.factsViewModel = viewModel
        factsViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        showFirstFact()
    }

    private fun initListeners() {
        facts_back_button?.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun showFirstFact() {
        factsViewModel.getCatImage().observe(this, Observer {
            Picasso.with(context).load(it.first().url).into(facts_image)
        })
    }
}