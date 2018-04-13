package effexor.roman.nikonovich.presentation.screens.formFilling;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.spinnerMake.setAdapter(viewModel.spinMakeAdapter);
        binding.spinnerModel.setAdapter(viewModel.spinModelAdapter);
        binding.spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.changeData(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
