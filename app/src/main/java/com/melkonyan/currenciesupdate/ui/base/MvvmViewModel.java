package com.melkonyan.currenciesupdate.ui.base;

import android.databinding.Observable;
import android.os.Bundle;

public interface MvvmViewModel<V extends MvvmView> extends Observable {
    void attachView(V view, Bundle savedInstanceState);

    void detachView();

    void saveInstanceState(Bundle outState);

    void restoreInstanceState(Bundle restoredState);
}