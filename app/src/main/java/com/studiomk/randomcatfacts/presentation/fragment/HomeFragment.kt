package com.studiomk.randomcatfacts.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.studiomk.randomcatfacts.R
import com.studiomk.randomcatfacts.presentation.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        begin_button?.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    fun createViewModel(): HomeViewModel {
        return HomeViewModel()
    }
}