package com.example.injection.component

import com.example.injection.PerFragment
import com.example.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
@PerFragment
@Subcomponent(
    modules = [
        FragmentModule::class
    ]
)
interface FragmentComponent {
}