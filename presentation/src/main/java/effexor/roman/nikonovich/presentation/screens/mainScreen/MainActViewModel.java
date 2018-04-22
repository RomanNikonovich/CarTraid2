package effexor.roman.nikonovich.presentation.screens.mainScreen;


import android.content.Intent;

import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.iterators.GetSearchListUseCase;
import effexor.roman.nikonovich.presentation.base.BaseAdapter;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.formFilling.AddNewSearch;
import effexor.roman.nikonovich.presentation.screens.mainScreen.realiseRV.SearchAdapter;
import effexor.roman.nikonovich.presentation.screens.searchCar.SearchCars;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainActViewModel extends BaseViewModel {
    public SearchAdapter adapter = new SearchAdapter();
    public static final String ID_SEARCH = "idSearch";
/*    private CheckInternetBroadcast broadcast;*/

    @Inject
    public GetSearchListUseCase searchListUseCase;

    public void addSearch() {
/*        broadcast = new CheckInternetBroadcast();
        router.getActivity().registerReceiver(broadcast, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));*/
        router
                .getActivity()
                .startActivity(new Intent(router.getActivity(), AddNewSearch.class));


    }

    public MainActViewModel() {
        App.getAppComponent().inject(this);
        compositeDisposable.add(searchListUseCase
                .searchList()
                .subscribeWith(new DisposableSubscriber<List<Search>>() {
                    @Override
                    public void onNext(List<Search> searches) {
                        adapter.setItems(searches);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
        compositeDisposable.add(adapter.observeClick()
                .subscribe(new Consumer<BaseAdapter.ItemEntity>() {
                    @Override
                    public void accept(BaseAdapter.ItemEntity itemEntity) throws Exception {
                        Search search = (Search) itemEntity.model;
                        Intent intent = new Intent(router.getActivity(), SearchCars.class);
                        intent.putExtra(ID_SEARCH, search.getIdSearch());
                        router.getActivity().startActivity(intent);
                    }
                }));
    }

}
