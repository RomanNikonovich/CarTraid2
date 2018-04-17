package effexor.roman.nikonovich.presentation.screens.searchCar.realiseRV;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import effexor.roman.nikonovich.databinding.ItemCarRvBinding;
import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.presentation.base.BaseItemViewHolder;

public class CarViewHolderRV extends BaseItemViewHolder<Vehicle, CarViewModelRV, ItemCarRvBinding> {
    public CarViewHolderRV(ItemCarRvBinding binding, CarViewModelRV viewModel) {
        super(binding, viewModel);
    }

    public static CarViewHolderRV create(ViewGroup parent, CarViewModelRV carViewModel) {
        ItemCarRvBinding binding = ItemCarRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarViewHolderRV(binding, carViewModel);
    }
}
