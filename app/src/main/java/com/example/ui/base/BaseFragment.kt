package com.example.ui.base

import android.os.Bundle
import androidx.collection.LongSparseArray
import androidx.fragment.app.Fragment
import com.example.BoilerplateApplication
import com.example.injection.component.ConfigPersistentComponent
import com.example.injection.component.DaggerConfigPersistentComponent
import com.example.injection.component.FragmentComponent
import com.example.injection.module.FragmentModule
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
class BaseFragment : Fragment() {

    private var mFragmentComponent: FragmentComponent? = null

    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()

        var configPersistentComponent = sComponentsMap[mActivityId, null]
        if (configPersistentComponent == null) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(BoilerplateApplication[requireActivity()].component)
                .build()

            sComponentsMap.put(mActivityId, configPersistentComponent)
        }

        mFragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mActivityId)
    }

    override fun onDestroyView() {
        if (!requireActivity().isChangingConfigurations) {
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroyView()
    }

    fun fragmentComponent(): FragmentComponent {
        return mFragmentComponent!! // assert !! to explain it will not be null
    }

    companion object {
        private const val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val NEXT_ID = AtomicLong(0)
        private val sComponentsMap = LongSparseArray<ConfigPersistentComponent>()
    }
}