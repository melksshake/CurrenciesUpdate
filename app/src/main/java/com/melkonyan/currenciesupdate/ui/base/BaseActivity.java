package com.melkonyan.currenciesupdate.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.melkonyan.currenciesupdate.BR;

import icepick.Icepick;

/**
 * Base class for Activities when using a view model with data binding.
 * This class provides the binding and the view model to the subclass. The
 * view model is injected and the binding is created when the content view is set.
 * Each subclass therefore has to call the following code in onCreate():
 * activityComponent().inject(this);
 * setAndBindContentView(R.layout.my_activity_layout, savedInstanceState);
 * <p>
 * After calling these methods, the binding and the view model is initialized.
 * saveInstanceState() and restoreInstanceState() methods of the view model
 * are automatically called in the appropriate lifecycle events when above calls
 * are made.
 * <p>
 * Your subclass must implement the MvvmView implementation that you use in your
 * view model.
 */
public class BaseActivity<B extends ViewDataBinding, V extends MvvmViewModel> extends AppCompatActivity {
    //    @Inject protected V viewModel;
    protected V viewModel;
    protected B binding;

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        if (viewModel != null) {
            viewModel.saveInstanceState(outState);
        }
    }

    // TODO refwatcher
    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.detachView();
        }
        binding = null;
        viewModel = null;
    }

//    protected final ActivityComponent activityComponent() {
//        if (activityComponent == null) {
//            activityComponent = DaggerActivityComponent.builder()
//                    .activityModule(new ActivityModule(this))
//                    .appComponent(TelemedApp.app(this).appComponent())
//                    .build();
//        }
//        return activityComponent;
//    }

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected final void setAndBindContentView(@Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
//        if (viewModel == null) {
//            throw new IllegalStateException("viewModel must already be set via injection");
//        }
        binding = DataBindingUtil.setContentView(this, layoutResID);
//        binding.setVariable(BR.vm, viewModel);

        try {
            //noinspection unchecked
            viewModel.attachView((MvvmView) this, savedInstanceState);
            if (savedInstanceState != null) {
                binding.executePendingBindings();
            }
        } catch (ClassCastException e) {
//            if (!(viewModel instanceof NoOpViewModel)) {
//                throw new RuntimeException(
//                        getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + viewModel.getClass().getSimpleName());
//            }
        }
    }

    @Override
    public void onBackPressed() {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.holder_fragment_container);
//        if (fragment instanceof FragmentHelper && fragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
//            fragment.getChildFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
        this.finish();
    }

    public int dimen(@DimenRes int resId) {
        return (int) getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

    public int integer(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }

    public String string(@StringRes int resId) {
        return getResources().getString(resId);
    }
}
