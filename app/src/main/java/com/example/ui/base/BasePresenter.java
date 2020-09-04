package com.example.ui.base;

import androidx.annotation.NonNull;
import com.example.util.RxUtil;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    private CompositeDisposable mDisposableContainer;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        RxUtil.dispose(mDisposableContainer);
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
        return mMvpView;
    }

    @NonNull
    public CompositeDisposable getDisposableContainer() {
        if (mDisposableContainer == null || mDisposableContainer.isDisposed()) {
            mDisposableContainer = new CompositeDisposable();
        }

        return mDisposableContainer;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}

