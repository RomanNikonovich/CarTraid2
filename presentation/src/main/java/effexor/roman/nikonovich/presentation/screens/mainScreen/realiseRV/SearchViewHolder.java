package effexor.roman.nikonovich.presentation.screens.mainScreen.realiseRV;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import effexor.roman.nikonovich.databinding.ItemSearchRvBinding;
import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.presentation.base.BaseItemViewHolder;

public class SearchViewHolder extends BaseItemViewHolder<Search, SearchViewModel, ItemSearchRvBinding> {

    public SearchViewHolder(ItemSearchRvBinding binding, SearchViewModel viewModel) {
        super(binding, viewModel);
    }

    public static SearchViewHolder create(ViewGroup parent, SearchViewModel searchViewModel) {
        ItemSearchRvBinding binding = ItemSearchRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(binding, searchViewModel);
    }
}
