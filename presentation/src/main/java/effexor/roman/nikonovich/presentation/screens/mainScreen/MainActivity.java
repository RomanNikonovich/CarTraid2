package effexor.roman.nikonovich.presentation.screens.mainScreen;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityMainBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class MainActivity extends
        BaseActivity<ActivityMainBinding, MainActViewModel, MainActRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActViewModel.class);
    }

    @Override
    public MainActRouter provideRouter() {
        return new MainActRouter(this);
    }

}
