package effexor.roman.nikonovich.data.repostitory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.data.entity.vehicleNet.SearchNet;
import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import effexor.roman.nikonovich.domain.entity.vehicle.Car;
import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SearchVehicleRepositoryImpl implements SearchVehicleRepository {
    private Realm realm;

    @Inject
    public SearchVehicleRepositoryImpl() {
    }

    @Override
    public Completable addSearch(final String url, final String nameSearch) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.insert(new SearchNet(nameSearch, url));
                realm.commitTransaction();
                realm.close();
                emitter.onComplete();
            }
        });
    }

    @Override
    public Flowable<List<Search>> getSearchList() {
        realm = Realm.getDefaultInstance();
        return realm
                .where(SearchNet.class)
                .findAllAsync()
                .asFlowable()
                .map(new Function<RealmResults<SearchNet>, List<Search>>() {
                    @Override
                    public List<Search> apply(RealmResults<SearchNet> searchNets) throws Exception {
                        List<Search> list = new ArrayList<>();
                        for (SearchNet search : searchNets) {
                            list.add(new Search(search.getIdSearch(), search.getNameSearch()));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Flowable<List<Car>> getCars(String id) {
        realm = Realm.getDefaultInstance();
        return realm
                .where(SearchNet.class)
                .equalTo("idSearch", id)
                .findFirst()
                .asFlowable()
                .map(new Function<RealmObject, List<Car>>() {
                    @Override
                    public List<Car> apply(RealmObject realmObject) throws Exception {
                        SearchNet search = (SearchNet) realmObject;
                        List<Car> cars = new ArrayList<>();
                        for (VehicleNet vehicle : search.getListVehicleNet()) {
                            cars.add(new Car(vehicle.getUrl(), vehicle.getMake(), vehicle.getPriceRUB(), vehicle.getPriceUSD()));
                        }
                        return cars;
                    }
                });
    }
}
