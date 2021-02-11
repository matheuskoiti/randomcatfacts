package com.studiomk.randomcatfacts.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studiomk.randomcatfacts.R
import com.studiomk.randomcatfacts.presentation.fragment.DogFactsFragment
import com.studiomk.randomcatfacts.presentation.fragment.FactsFragment
import com.studiomk.randomcatfacts.presentation.fragment.PandaFactsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirstFragment()
        initNavigationListeners()
    }

    private fun initFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, FactsFragment())
            .commit()
    }

    private fun initNavigationListeners() {
        main_navigation?.setOnNavigationItemSelectedListener { itemId ->
            when(itemId.itemId) {
                R.id.tab_cat -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, FactsFragment())
                        .commit()
                    true
                }
                R.id.tab_dog -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, DogFactsFragment())
                        .commit()
                    true
                }
                R.id.tab_panda -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, PandaFactsFragment())
                        .commit()
                    true
                }
                else -> true
            }
        }
    }
}