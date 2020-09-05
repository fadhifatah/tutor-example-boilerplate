package com.example.ui.main;

import com.example.base.MvpView;
import com.example.data.model.User;

public interface MainMvpView extends MvpView {

    void onSetUser(User user);
}
