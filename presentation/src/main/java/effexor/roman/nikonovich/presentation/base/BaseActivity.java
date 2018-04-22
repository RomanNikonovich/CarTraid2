package effexor.roman.nikonovich.presentation.base;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.databinding.library.baseAdapters.BR;

import effexor.roman.nikonovich.presentation.screens.aboutApp.AboutApp;
import effexor.roman.nikonovich.presentation.screens.contactDeveloper.ContactDeveloper;
import effexor.roman.nikonovich.presentation.screens.setting.Setting;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(effexor.roman.nikonovich.R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case effexor.roman.nikonovich.R.id.actionSearch:
                startActivity(new Intent(this, Setting.class));
                break;
            case effexor.roman.nikonovich.R.id.actionSearch2:
                startActivity(new Intent(this, ContactDeveloper.class));
                break;
            case effexor.roman.nikonovich.R.id.actionSearch3:
                startActivity(new Intent(this, AboutApp.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
