package effexor.roman.nikonovich.presentation.screens.searchCar.realiseRV;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.presentation.base.BaseItemViewModel;

public class CarViewModelRV extends BaseItemViewModel<Vehicle> {
    public final ObservableField<String> make = new ObservableField<>();
    public final ObservableField<String> priceRUB = new ObservableField<>();
    public final ObservableField<String> priceUSD = new ObservableField<>();
    public final ObservableBoolean isNew = new ObservableBoolean(false);

    @Override
    public void setItem(Vehicle vehicle, int position) {
        make.set(vehicle.getMake());
        priceRUB.set(vehicle.getPriceRUB());
        priceUSD.set(vehicle.getPriceUSD());
    }
}
