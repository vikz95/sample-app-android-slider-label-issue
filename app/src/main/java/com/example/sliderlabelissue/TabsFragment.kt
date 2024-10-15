package com.example.sliderlabelissue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout

class TabsFragment : Fragment() {

    private var tabFragments = listOf(
        TabFragment(),
        TabFragment()
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tabs_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.tab_fragment_container, tabFragments[0], "Tab1")
            add(R.id.tab_fragment_container, tabFragments[1], "Tab2")
            hide(tabFragments[1])
        }

        view.findViewById<TabLayout>(R.id.tabs).addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                childFragmentManager.commit {
                    tab?.let { show(tabFragments[it.position]) }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                childFragmentManager.commit {
                    tab?.let { hide(tabFragments[it.position]) }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

}
