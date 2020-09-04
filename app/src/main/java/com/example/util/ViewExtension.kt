package com.example.util

import android.view.View

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}