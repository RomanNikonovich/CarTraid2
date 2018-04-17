package effexor.roman.nikonovich.domain.repository;

import java.util.List;

import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface SearchVehicleRepository {
    Completable addSearch(String url, String nameSearch);

    Flowable<List<Search>> getSearchList();

    Flowable<List<Vehicle>> getCars(String id);
}
