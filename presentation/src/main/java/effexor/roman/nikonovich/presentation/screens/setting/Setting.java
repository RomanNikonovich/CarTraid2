package effexor.roman.nikonovich.presentation.screens.setting;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.data.sharedPref.AppSharedPrefs;
import effexor.roman.nikonovich.databinding.ActivitySettingBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class Setting extends
        BaseActivity<ActivitySettingBinding, SettingViewModel, SettingRouter> {
    @Inject
    public AppSharedPrefs sharedPrefs;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent().inject(this);

        binding.spinSchedule.setSelection(sharedPrefs.getPosition());
        binding.spinSchedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.position.set(i);
                if (binding.spinSchedule.getSelectedItem().toString().equals(getResources()
                        .getStringArray(R.array.schedule_number)[0])) {
                    viewModel.millisec.set(0L);
                } else if (1 <= i && i <= 3) {
                    String temp = binding.spinSchedule.getSelectedItem()
                            .toString().replaceAll("\\D", "");
                    viewModel.millisec.set(TimeUnit.MINUTES.toMillis(Long.valueOf(temp)));
                } else {
                    String temp = binding.spinSchedule.getSelectedItem()
                            .toString().replaceAll("\\D", "");
                    viewModel.millisec.set(TimeUnit.HOURS.toMillis(Long.valueOf(temp)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
