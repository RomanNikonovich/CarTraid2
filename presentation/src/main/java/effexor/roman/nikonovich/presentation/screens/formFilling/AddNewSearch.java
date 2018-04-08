package effexor.roman.nikonovich.presentation.screens.formFilling;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityAddNewSearchBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class AddNewSearch extends
        BaseActivity<ActivityAddNewSearchBinding, AddSearchViewModel, NewSearchRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_add_new_search;
    }

    @Override
    public AddSearchViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(AddSearchViewModel.class);
    }

    @Override
    public NewSearchRouter provideRouter() {
        return new NewSearchRouter(this);
    }

}
