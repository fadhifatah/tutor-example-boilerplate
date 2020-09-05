package com.example.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.base.getStartInstance
import com.example.injection.ConfigPersistent
import com.example.ui.main.page.ReminderFragment
import com.example.util.Extras
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Sat, 05 Sep 2020
 */

class ReminderPageAdapter @Inject constructor(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = getStartInstance<ReminderFragment>(Bundle().apply {
        putString(Extras.TYPE, if (position == 0) "ongoing" else "completed")
    }, ReminderFragment())
}