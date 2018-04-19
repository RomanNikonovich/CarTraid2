package effexor.roman.nikonovich.presentation.screens.searchCar.realiseRV;

import android.view.ViewGroup;

import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.presentation.base.BaseAdapter;
import effexor.roman.nikonovich.presentation.base.BaseItemViewHolder;

public class CarAdapterRV extends BaseAdapter<Vehicle, CarViewModelRV> {

    public CarAdapterRV() {
        isClickable = true;
    }

    @Override
    public BaseItemViewHolder<Vehicle, CarViewModelRV, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        return CarViewHolderRV.create(parent, new CarViewModelRV());
    }

}
