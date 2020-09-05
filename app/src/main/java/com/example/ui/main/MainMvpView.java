package com.example.ui.main;

import com.example.data.model.Reminder;
import com.example.data.model.User;
import com.example.ui.base.MvpView;
import java.util.List;

public interface MainMvpView extends MvpView {

    void hideLoading();

    void onSetUser(User user);

    void onSuccess(List<Reminder> dataList);

    void showError(String message);

    void showLoading();
}
