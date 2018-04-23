package effexor.roman.nikonovich.presentation.screens.mainScreen;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.iterators.DeleteSearchUseCase;
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
    private AlertDialog.Builder ad;

    @Inject
    public GetSearchListUseCase searchListUseCase;

    @Inject
    public DeleteSearchUseCase deleteSearchUseCase;

    public void addSearch() {
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
        compositeDisposable.add(adapter.observeClickLong()
                .subscribe(new Consumer<BaseAdapter.ItemEntity>() {
                    @Override
                    public void accept(BaseAdapter.ItemEntity itemEntity) throws Exception {
                        Search search = (Search) itemEntity.model;
                        delete(search.getNameSearch(), search.getIdSearch());
                    }
                }));
    }

    private void delete(String title, final String id) {
        ad = new AlertDialog
                .Builder(router.getActivity())
                .setTitle("Удалить поиск \"" + title + "\"?")
                .setPositiveButton(router.getActivity().getResources().getString(R.string.no)
                        , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                return;
                            }
                        })
                .setNegativeButton(router.getActivity().getResources().getString(R.string.yes)
                        , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                deleteSearchUseCase
                                        .deleteSearch(id)
                                        .subscribe();
                            }
                        })
                .setCancelable(true);
        ad.show();
    }

}
