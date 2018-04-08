package effexor.roman.nikonovich.presentation.screens.searchCar;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivitySearchCarsBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class SearchCars extends
        BaseActivity<ActivitySearchCarsBinding, SearchCarsViewModel, SearchCarRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_search_cars;
    }

    @Override
    public SearchCarsViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(SearchCarsViewModel.class);
    }

    @Override
    public SearchCarRouter provideRouter() {
        return new SearchCarRouter(this);
    }

}
