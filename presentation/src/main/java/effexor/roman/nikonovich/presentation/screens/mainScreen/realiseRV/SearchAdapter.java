package effexor.roman.nikonovich.presentation.screens.mainScreen.realiseRV;

import android.view.ViewGroup;

import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.presentation.base.BaseAdapter;
import effexor.roman.nikonovich.presentation.base.BaseItemViewHolder;

public class SearchAdapter extends BaseAdapter<Search, SearchViewModel> {

    public SearchAdapter() {
        isClickable = true;
    }

    @Override
    public BaseItemViewHolder<Search, SearchViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchViewHolder.create(parent, new SearchViewModel());
    }
}
