package effexor.roman.nikonovich.presentation.screens.formFilling;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityAddNewSearchBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class AddNewSearch extends
        BaseActivity<ActivityAddNewSearchBinding, AddSearchViewModel, NewSearchRouter>
        implements AdapterView.OnItemSelectedListener {

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
        binding.spinnerMake.setOnItemSelectedListener(this);

        binding.spinnerModel.setAdapter(viewModel.spinModelAdapter);
        binding.spinnerModel.setOnItemSelectedListener(this);

        binding.spinnerFrom.setOnItemSelectedListener(this);
        binding.spinnerTo.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinnerMake:
                if (view != null) {
                    TextView idMake = view.findViewById(R.id.textId);
                    viewModel.setIdMake(idMake.getText().toString());
                    viewModel.changeData(i);
                    binding.spinnerModel.setSelection(0);
                }
                break;
            case R.id.spinnerModel:
                if (view != null) {
                    TextView idModel = view.findViewById(R.id.textId);
                    viewModel.setIdModel(idModel.getText().toString());
                }
                break;
            case R.id.spinnerFrom:
                if (binding.spinnerFrom.getSelectedItem().toString()
                        .equals(getResources().getStringArray(R.array.chooseYearFrom)[0])) {
                    viewModel.setYearFrom("");
                } else {
                    viewModel.setYearFrom(binding.spinnerFrom.getSelectedItem().toString());
                }
                break;
            case R.id.spinnerTo:
                if (binding.spinnerTo.getSelectedItem().toString()
                        .equals(getResources().getStringArray(R.array.chooseYearTo)[0])) {
                    viewModel.setYearTo("");
                } else {
                    viewModel.setYearTo(binding.spinnerTo.getSelectedItem().toString());
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
