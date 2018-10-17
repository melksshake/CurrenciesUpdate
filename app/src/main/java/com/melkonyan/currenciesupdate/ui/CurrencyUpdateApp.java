package com.melkonyan.currenciesupdate.ui;

import android.app.Application;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import com.melkonyan.currenciesupdate.BuildConfig;
import timber.log.Timber;

public class CurrencyUpdateApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    installTimber();
    installVectorFromResources();
    Timber.d("App has been initialized.");


  }

  private void installTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  protected void installVectorFromResources() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
  }
}
