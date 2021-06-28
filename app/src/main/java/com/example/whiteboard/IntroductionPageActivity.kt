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

//ページの総数
private const val NUM_PAGES = 3

/**
 * 初回起動時に表示するintroductionページのActivity
 */
class IntroductionPageActivity : FragmentActivity() {
    //初回の文字をいくつかのページに分けていい感じにする用viewPager
    private lateinit var viewPager: ViewPager2

    //インジケータたちを画面に表示する場所
    private lateinit var indicatorArea: LinearLayout

    //インジケータ実体たちが入ってるリスト
    private var indicatorViewList: ArrayList<View> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_page)

        //viewPager
        viewPager = findViewById(R.id.pager)

        //viewPagerのAdapterにIntroductionPageAdapterを設定
        val pagerAdapter = IntroductionPageAdapter(this)
        viewPager.adapter = pagerAdapter

        //ページが切り替えられたときの処理を設定、object:foo()は、foo()オブジェクトで、{}の中でoverrideができる。すごい
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                //スクロールされたときに対応するインジケータの表示色を更新する
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

        //インジケータを入れるとこ
        indicatorArea = findViewById(R.id.indicator_area)

        //インジケータの値たちをファイルから取ってくる
        val indicatorWidth = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorHeight = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorMarginStart =
            resources.getDimension(R.dimen.walk_through_indicator_margin_start).toInt()

        //onCreate時のデフォルトインジケーター表示をつくるfor
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
        //戻るボタンが押されたときの処理、currentItem == 0のときの動作は要検証、アプリを初めて開いたときに戻るボタン押下なのでホームに戻るようにしたい
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    //初回の文字をいくつかのページに分けていい感じにする用viewPagerで使用するPageAdapter、getItemCountとcreateFragmentをそれぞれ設定してるだけ
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