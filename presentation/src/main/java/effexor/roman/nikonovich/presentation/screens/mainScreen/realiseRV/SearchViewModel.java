package effexor.roman.nikonovich.presentation.screens.mainScreen.realiseRV;

import android.databinding.ObservableField;

import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.presentation.base.BaseItemViewModel;

public class SearchViewModel extends BaseItemViewModel<Search> {
    public final ObservableField<String> nameSearch = new ObservableField<>("");
    public final ObservableField<String> idSearch = new ObservableField<>("");

    @Override
    public void setItem(Search search, int position) {
        nameSearch.set(search.getNameSearch());
        idSearch.set(search.getIdSearch());
    }
}
