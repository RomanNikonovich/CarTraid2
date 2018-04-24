package effexor.roman.nikonovich.data.repostitory;

import android.content.Context;

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
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SearchVehicleRepositoryImpl implements SearchVehicleRepository {
    private Realm realm;
    private static final String ID_SEARCH = "idSearch";
    private Context context;

    @Inject
    public SearchVehicleRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Completable addSearch(final String url, final String nameSearch, final int price) {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                try (Realm realmInstance = Realm.getDefaultInstance()) {
                    realmInstance.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            final SearchNet searchNet = new SearchNet(nameSearch, url, price);
                            try {
                                RealmList<VehicleNet> carsList = ParseUrl.getCars(url);
                                realm.insert(searchNet);
                                SearchNet search = realm
                                        .where(SearchNet.class)
                                        .equalTo(ID_SEARCH, searchNet.getIdSearch())
                                        .findFirst();
                                search.getListVehicleNet().addAll(carsList);
                            } catch (IOException e) {
                                realm.insert(searchNet);
                            }

                        }
                    });
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable deleteSearch(final String id) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try (Realm realmInstance = Realm.getDefaultInstance()) {
                    realmInstance.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SearchNet search = realm
                                    .where(SearchNet.class)
                                    .equalTo(ID_SEARCH, id)
                                    .findFirst();
                            search.deleteFromRealm();
                        }
                    });
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable changeState(final String idSearch, final String idCar) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try (Realm realmInstance = Realm.getDefaultInstance()) {
                    realmInstance.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                                SearchNet search = realm
                                        .where(SearchNet.class)
                                        .equalTo(ID_SEARCH, idSearch)
                                        .findFirst();
                                for(VehicleNet net: search.getListVehicleNet()){
                                    if(net.getUrl().equals(idCar)){
                                        net.setNew(false);
                                        return;
                                    }
                                }
                        }
                    });
                }
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
                            int count = 0;
                            for (VehicleNet net : search.getListVehicleNet()) {
                                if (net.isNew())
                                    count++;
                            }
                            list.add(new Search(search.getIdSearch(),
                                    search.getNameSearch(),
                                    search.getDateCreate(),
                                    count));
                        }
                        return list;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        realm.close();
                    }
                });

    }

    @Override
    public Flowable<List<Vehicle>> getCars(String id) {
        realm = Realm.getDefaultInstance();
        return realm
                .where(SearchNet.class)
                .equalTo(ID_SEARCH, id)
                .findFirst()
                .asFlowable()
                .filter(new Predicate<RealmObject>() {
                    @Override
                    public boolean test(RealmObject realmObject) throws Exception {
                        return realmObject.isLoaded();
                    }
                })
                .map(new Function<RealmObject, List<Vehicle>>() {
                    @Override
                    public List<Vehicle> apply(RealmObject realmObject) throws Exception {
                        List<Vehicle> vehicles = new ArrayList<>();
                        for (VehicleNet vehicle : ((SearchNet) realmObject).getListVehicleNet()) {
                            vehicles.add(new Vehicle(vehicle.getUrl(),
                                    vehicle.getMake(),
                                    vehicle.getPriceRUB(),
                                    vehicle.getPriceUSD(),
                                    ((SearchNet) realmObject).getPrice(),
                                    vehicle.isNew()));
                        }
                        return vehicles;
                    }
                });
    }
}
