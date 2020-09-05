package com.example.ui.main;

import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.base.BaseActivity;
import com.example.base.BaseService;
import com.example.data.model.User;
import com.example.data.service.SyncService;
import com.example.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;

    @Inject
    ReminderPageAdapter mReminderPageAdapter;

    @Override
    protected ActivityMainBinding onCreateViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);

        mBinding.vpReminder.setAdapter(mReminderPageAdapter);
        new TabLayoutMediator(mBinding.tabLayout, mBinding.vpReminder, true, (tab, position) -> {
            String[] title = new String[]{"Sedang Berjalan", "Selesai"};
            tab.setText(title[position]);
        }).attach();

        // mBinding.rvOngoing.setAdapter(mOngoingAdapter);
        // mBinding.rvOngoing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mMainPresenter.attachView(this);
        mMainPresenter.loadUser();

        startService(BaseService.getStartIntent(this, SyncService.class));
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onSetUser(final User user) {
        mBinding.tvName.setText(user.getName());
        mBinding.tvStatus.setText(user.getMessage());
        Glide.with(this).load(user.getPicture()).circleCrop().into(mBinding.ivProfile);
    }
}
