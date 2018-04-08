package effexor.roman.nikonovich.presentation.screens.setting;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivitySettingBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class Setting extends
        BaseActivity<ActivitySettingBinding, SettingViewModel, SettingRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public SettingViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(SettingViewModel.class);
    }

    @Override
    public SettingRouter provideRouter() {
        return new SettingRouter(this);
    }

}
