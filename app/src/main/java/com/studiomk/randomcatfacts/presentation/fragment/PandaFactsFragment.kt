package com.studiomk.randomcatfacts.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.studiomk.randomcatfacts.R
import com.studiomk.randomcatfacts.databinding.FragmentPandaFactsBinding
import com.studiomk.randomcatfacts.presentation.viewModel.PandaFactsViewModel
import kotlinx.android.synthetic.main.fragment_facts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PandaFactsFragment : Fragment() {
    //This is the way without Koin injection
    //val viewModel = ViewModelProviders.of(this).get(CatFactsViewModel::class.java)
    private val pandaFactsViewModel by viewModel<PandaFactsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPandaFactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_panda_facts, container, false)
        binding.pandaFactsViewModel = pandaFactsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFirstFact()
    }

    private fun showFirstFact() {
        /**
         * I could just call factsViewModel.onNextFactClick() but I kept this way for study purposes
         */
        pandaFactsViewModel.getFirstPandaImage().observe(this, Observer {
            Picasso.with(context).load(it.link).into(facts_image)
        })
        pandaFactsViewModel.getFirstPandaFact().observe(this, Observer {
            facts_message?.text = it.fact
        })
    }
}