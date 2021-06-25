package com.example.whiteboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class UserInfoViewPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        return fragment!!
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }

    companion object {
        private const val PAGE_NUM = 3
    }
}