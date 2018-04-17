package effexor.roman.nikonovich.data.repostitory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.data.entity.vehicleNet.SearchNet;
import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import effexor.roman.nikonovich.data.utils.ParseUrl;
import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmList;
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
                final SearchNet searchNet = new SearchNet(nameSearch, url);
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.insert(searchNet);
                realm.commitTransaction();
                realm.close();
                realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        try {
                            RealmList<VehicleNet> carsList = ParseUrl.getCars(url);
                            SearchNet search = realm
                                    .where(SearchNet.class)
                                    .equalTo("idSearch", searchNet.getIdSearch())
                                    .findFirst();
                            search.setListVehicleNet(carsList);
                            search.setNameSearch("Sucses");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
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
    public Flowable<List<Vehicle>> getCars(String id) {
        realm = Realm.getDefaultInstance();
        return realm
                .where(SearchNet.class)
                .equalTo("idSearch", id)
                .findFirstAsync()
                .asFlowable()
                .map(new Function<RealmObject, List<Vehicle>>() {
                    @Override
                    public List<Vehicle> apply(RealmObject realmObject) throws Exception {
                        SearchNet search = (SearchNet) realmObject;
                        List<Vehicle> vehicles = new ArrayList<>();
                        for (effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet vehicle : search.getListVehicleNet()) {
                            vehicles.add(new Vehicle(vehicle.getUrl(), vehicle.getMake(), vehicle.getPriceRUB(), vehicle.getPriceUSD()));
                        }
                        return vehicles;
                    }
                });
    }
}
