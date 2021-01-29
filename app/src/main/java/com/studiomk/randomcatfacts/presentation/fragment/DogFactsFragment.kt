package com.studiomk.randomcatfacts.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.studiomk.randomcatfacts.R
import com.studiomk.randomcatfacts.databinding.FragmentDogFactsBinding
import com.studiomk.randomcatfacts.databinding.FragmentFactsBinding
import com.studiomk.randomcatfacts.presentation.viewModel.CatFactsViewModel
import com.studiomk.randomcatfacts.presentation.viewModel.DogFactsViewModel
import kotlinx.android.synthetic.main.fragment_dog_facts.*
import kotlinx.android.synthetic.main.fragment_facts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogFactsFragment : Fragment() {
    //This is the way without Koin injection
    //val viewModel = ViewModelProviders.of(this).get(DogFactsViewModel::class.java)
    private val dogFactsViewModel by viewModel<DogFactsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentDogFactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_facts, container, false)
        binding.dogFactsViewModel = dogFactsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFirstFact()
    }

    private fun showFirstFact() {
        /**
         * I could just call dogFactsViewModel.onNextFactClick() but I kept this way for study purposes
         */
        dogFactsViewModel.getFirstDogFact().observe(this, Observer {
            dog_facts_message?.text = it.facts.first()
        })
        dogFactsViewModel.getFirstDogImage().observe(this, Observer {
            Picasso.with(context).load(it.message).into(dog_facts_image)
        })
    }

}