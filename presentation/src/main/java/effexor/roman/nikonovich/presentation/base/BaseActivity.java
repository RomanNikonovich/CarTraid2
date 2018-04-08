package effexor.roman.nikonovich.presentation.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;

public abstract class BaseActivity<Binding extends ViewDataBinding,
        ViewModel extends BaseViewModel,
        R extends BaseRouter> extends AppCompatActivity {

    protected Binding binding;
    protected ViewModel viewModel;
    protected R router;

    public abstract int provideLayoutId();
    public abstract ViewModel provideViewModel();
    public abstract R provideRouter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = provideViewModel();
        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel, viewModel);
        viewModel.onCreate();
        router = provideRouter();
        viewModel.attachRouter(router);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
        router = null;
        viewModel.detachRouter();
    }
}
