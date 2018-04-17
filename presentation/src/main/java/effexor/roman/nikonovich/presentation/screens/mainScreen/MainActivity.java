package effexor.roman.nikonovich.presentation.screens.mainScreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.data.sharedPref.AppSharedPrefs;
import effexor.roman.nikonovich.databinding.ActivityMainBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class MainActivity extends
        BaseActivity<ActivityMainBinding, MainActViewModel, MainActRouter> {
    private int countGide = 0;
    @Inject
    public AppSharedPrefs sharedPrefs;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(viewModel.adapter);

        if (sharedPrefs.getTipsShow())
            showTips(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        countGide = 0;
        showTips(null);
    }

    public void showTips(View view) {
        if (countGide == 0) {
            binding.backgroundTips.setVisibility(View.VISIBLE);
            binding.btnAddSearch.bringToFront();
            binding.newSearch.setVisibility(View.VISIBLE);
            binding.firstTip.setVisibility(View.VISIBLE);
            binding.nextTip.setVisibility(View.VISIBLE);
            countGide++;
        } else if (countGide == 1) {
            binding.backgroundTips.bringToFront();
            binding.newSearch.setVisibility(View.GONE);
            binding.firstTip.setVisibility(View.GONE);
            binding.nextTip.setVisibility(View.GONE);
            binding.setting.setVisibility(View.VISIBLE);
            binding.setting.bringToFront();
            binding.secondTip.setVisibility(View.VISIBLE);
            binding.secondTip.bringToFront();
            binding.nextTip2.setVisibility(View.VISIBLE);
            countGide++;
        } else if(countGide == 2){
            binding.setting.setVisibility(View.GONE);
            binding.secondTip.setVisibility(View.GONE);
            binding.nextTip2.setVisibility(View.GONE);
            binding.showSearch.setVisibility(View.VISIBLE);
            binding.showSearch.bringToFront();
            binding.thirdTip.setVisibility(View.VISIBLE);
            binding.thirdTip.bringToFront();
            binding.nextTip3.setVisibility(View.VISIBLE);
            countGide++;
        }else {
            binding.backgroundTips.setVisibility(View.GONE);
            binding.showSearch.setVisibility(View.GONE);
            binding.thirdTip.setVisibility(View.GONE);
            binding.nextTip3.setVisibility(View.GONE);
            sharedPrefs.saveTipsShow();
        }

    }
}
