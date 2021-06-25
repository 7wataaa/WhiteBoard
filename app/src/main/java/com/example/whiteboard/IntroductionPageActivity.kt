package com.example.whiteboard

import IntroductionPage1Fragment
import IntroductionPage2Fragment
import IntroductionPage3Fragment
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import  androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

private const val NUM_PAGES = 3

class IntroductionPageActivity : FragmentActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorArea: LinearLayout
    private var indicatorViewList: ArrayList<View> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_page)

        viewPager = findViewById(R.id.pager)

        val pagerAdapter = IntroductionPageAdapter(this)
        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                for (i in 0 until indicatorViewList.size) {
                    if (i == position)
                        indicatorViewList[i].background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.indicator_view_active,
                            null
                        )
                    else
                        indicatorViewList[i].background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.indicator_view_inactive,
                            null
                        )
                }
            }
        })

        println("area id = ${R.id.indicator_area}")

        indicatorArea = findViewById(R.id.indicator_area)

        val indicatorWidth = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorHeight = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorMarginStart =
            resources.getDimension(R.dimen.walk_through_indicator_margin_start).toInt()

        for (i in 0..2) {
            val view = View(this)
            if (i == 0) {
                view.background = ContextCompat.getDrawable(this, R.drawable.indicator_view_active)

                val layoutParams = LinearLayout.LayoutParams(indicatorWidth, indicatorHeight)
                view.layoutParams = layoutParams
            } else {
                view.background =
                    ContextCompat.getDrawable(this, R.drawable.indicator_view_inactive)

                val layoutParams = LinearLayout.LayoutParams(indicatorWidth, indicatorHeight)
                layoutParams.marginStart = indicatorMarginStart
                view.layoutParams = layoutParams
            }
            indicatorArea.addView(view)
            indicatorViewList.add(view)
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class IntroductionPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> IntroductionPage1Fragment()
            1 -> IntroductionPage2Fragment()
            2 -> IntroductionPage3Fragment()
            else -> IntroductionPage1Fragment()

        }
    }
}