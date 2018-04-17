package effexor.roman.nikonovich.presentation.screens.launchAct;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityLaunchBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class LaunchAct extends BaseActivity<ActivityLaunchBinding, LaunchViewModel, LaunchRouter> {


    @Override
    public int provideLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public LaunchViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(LaunchViewModel.class);
    }

    @Override
    public LaunchRouter provideRouter() {
        return new LaunchRouter(this);
    }
}
