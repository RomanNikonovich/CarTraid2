package effexor.roman.nikonovich.presentation.screens.searchCar;

import android.content.Intent;
import android.net.Uri;

import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.domain.iterators.GetCarsUseCase;
import effexor.roman.nikonovich.presentation.base.BaseAdapter;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.searchCar.realiseRV.CarAdapterRV;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

import static effexor.roman.nikonovich.presentation.screens.mainScreen.MainActViewModel.ID_SEARCH;

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
                .getCars(router.getActivity().getIntent().getStringExtra(ID_SEARCH))
                .subscribeWith(new DisposableSubscriber<List<Vehicle>>() {
                    @Override
                    public void onNext(List<Vehicle> vehicles) {
                        adapterRV.setItems(vehicles);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
        compositeDisposable.add(adapterRV.observeClick()
                .subscribe(new Consumer<BaseAdapter.ItemEntity>() {
                    @Override
                    public void accept(BaseAdapter.ItemEntity itemEntity) throws Exception {
                        Vehicle vehicle = (Vehicle) itemEntity.model;
                        router.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(vehicle.getUrl())));
                    }
                }));
    }
}
