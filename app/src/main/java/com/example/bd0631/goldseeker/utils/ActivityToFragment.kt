package com.example.bd0631.goldseeker.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


class ActivityToFragment {

    companion object {
        public fun replaceActivityToFragment(fragmentManager: FragmentManager, fragment: Fragment, frameLayoutId: Int){
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameLayoutId, fragment)
            transaction.commit()
        }
    }


}