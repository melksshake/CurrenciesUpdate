package com.melkonyan.currenciesupdate.ui.mainscreen;

import com.melkonyan.currenciesupdate.ui.base.MvvmView;
import com.melkonyan.currenciesupdate.ui.base.MvvmViewModel;

public interface MainMvvm {
  interface View extends MvvmView {

  }

  interface ViewModel extends MvvmViewModel<View> {

  }
}
