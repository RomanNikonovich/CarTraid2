package effexor.roman.nikonovich.presentation.screens.searchCar;

import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.domain.iterators.GetCarsUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.searchCar.realiseRV.CarAdapterRV;
import io.reactivex.subscribers.DisposableSubscriber;

public class SearchCarsViewModel extends BaseViewModel {

    public CarAdapterRV adapterRV = new CarAdapterRV();

    @Inject
    public GetCarsUseCase carsUseCase;

    public SearchCarsViewModel() {
        App.getAppComponent().inject(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        compositeDisposable.add(carsUseCase
                .getCars(router.getActivity().getIntent().getStringExtra("idSearch"))
                .subscribeWith(new DisposableSubscriber<List<Vehicle>>() {
                    @Override
                    public void onNext(List<Vehicle> vehicles) {
                        List<Vehicle> vehi  = vehicles;
                        adapterRV.setItems(vehicles);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
