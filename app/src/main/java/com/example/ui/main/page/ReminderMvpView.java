package com.example.ui.main.page;

import com.example.base.MvpView;
import com.example.data.model.Reminder;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by fatahfadhlurrohman on Sat, 05 Sep 2020
 */
public interface ReminderMvpView extends MvpView {

    void hideLoading();

    void onSuccess(@NotNull final List<Reminder> reminderList);

    void showError(@Nullable final String message);

    void showLoading();
}
