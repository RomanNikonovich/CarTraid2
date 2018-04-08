package effexor.roman.nikonovich.presentation.screens.aboutApp;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityAboutAppBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class AboutApp extends
        BaseActivity<ActivityAboutAppBinding, AboutAppViewModel, AboutAppRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_about_app;
    }

    @Override
    public AboutAppViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(AboutAppViewModel.class);
    }

    @Override
    public AboutAppRouter provideRouter() {
        return new AboutAppRouter(this);
    }

}
