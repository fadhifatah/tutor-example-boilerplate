package com.example.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewbinding.ViewBinding;
import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;
import com.example.BoilerplateApplication;
import com.example.injection.component.ActivityComponent;
import com.example.injection.component.ConfigPersistentComponent;
import com.example.injection.component.DaggerConfigPersistentComponent;
import com.example.injection.module.ActivityModule;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
abstract public class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;

    public T mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }

        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));

        mBinding = onCreateViewBinding();
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    @NonNull
    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }


    /**
     * Simply create ViewBinding of this Fragment by implement this method,
     * then set mBinding = ...
     *
     * example: ActivityMainBinding.inflate(getLayoutInflater())
     */
    abstract protected T onCreateViewBinding();
}
