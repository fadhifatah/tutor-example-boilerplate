package com.example.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.LongSparseArray
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.BoilerplateApplication
import com.example.injection.component.ConfigPersistentComponent
import com.example.injection.component.DaggerConfigPersistentComponent
import com.example.injection.component.FragmentComponent
import com.example.injection.module.FragmentModule
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var mFragmentComponent: FragmentComponent? = null

    private var mActivityId: Long = 0

    lateinit var mBinding : T

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = onCreateViewBinding(inflater, container)
        return mBinding.root
    }

    /**
     * Simply create ViewBinding of this Fragment by implement this method,
     * then set mBinding = ...
     *
     * @param inflater a LayoutInflater that created by onCreateView method from Fragment
     * @param container a container for the Fragment
     *
     * example: FragmentReminderBinding.inflate(inflater, container, false)
     */
    abstract fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

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

        /*@JvmStatic
        fun <U> getStartInstance(bundle: Bundle, fragment: Fragment) : U = fragment.apply {
            arguments = bundle
        } as U*/
    }
}

inline fun <reified U> getStartInstance(bundle: Bundle, fragment: Fragment): U = fragment.apply {
    arguments = bundle
} as U