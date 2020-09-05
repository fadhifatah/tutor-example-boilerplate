package com.example.ui.main;

import com.example.data.DataManager;
import com.example.data.model.User;
import com.example.data.response.ReminderResponse;
import com.example.injection.ConfigPersistent;
import com.example.ui.base.BasePresenter;
import com.google.gson.Gson;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void loadMain() {
        getMvpView().showLoading();
        mDataManager.getDataList("ongoing")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ReminderResponse>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        getDisposableContainer().add(d);
                    }

                    @Override
                    public void onNext(final ReminderResponse response) {
                        if (response != null && response.getStatus() != null) {
                            if (response.getStatus().equals("success")) {
                                getMvpView().onSuccess(response.reminderList);
                                User user = new Gson().fromJson(mDataManager.getSavedUser(), User.class);
                                getMvpView().onSetUser(user);
                            } else {
                                getMvpView().showError(response.getMessage());
                            }
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(final Throwable e) {
                        getMvpView().showError(e.getMessage());
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onComplete() {}
                });
    }

}
