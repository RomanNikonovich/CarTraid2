package effexor.roman.nikonovich.presentation.screens.formFilling;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.entityChoose.MakeCar;
import effexor.roman.nikonovich.domain.iterators.GetChooseUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.formFilling.spinerAdapter.MakeAdapterSpinner;
import effexor.roman.nikonovich.presentation.screens.formFilling.spinerAdapter.ModelAdapterSpinner;
import effexor.roman.nikonovich.presentation.screens.formFilling.utils.SearchingURL;
import io.reactivex.subscribers.DisposableSubscriber;

public class AddSearchViewModel extends BaseViewModel {

    private ObservableField<String> nameSearch = new ObservableField<>("");
    public MakeAdapterSpinner spinMakeAdapter;
    public ModelAdapterSpinner spinModelAdapter;
    private List<MakeCar> makes = new ArrayList<>();
    private String idMake = "";
    private String idModel = "";
    private String yearFrom = "";
    private String yearTo = "";

    @Inject
    Context context;

    @Inject
    public GetChooseUseCase getChooseUseCase;

    public AddSearchViewModel() {
        App.getAppComponent().inject(this);
        spinMakeAdapter = new MakeAdapterSpinner(context, R.layout.spiner_drop_down_item);
        spinModelAdapter = new ModelAdapterSpinner(context, R.layout.spiner_drop_down_item);
        compositeDisposable
                .add(getChooseUseCase
                        .getChoose()
                        .subscribeWith(new DisposableSubscriber<List<MakeCar>>() {
                            @Override
                            public void onNext(List<MakeCar> makeCars) {
                                spinMakeAdapter.addAll(makeCars);
                                spinMakeAdapter.notifyDataSetChanged();
                                makes.addAll(makeCars);
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {
                            }
                        }));
    }

    public void setYearFrom(String yearFrom) {
        this.yearFrom = yearFrom;
    }

    public void setYearTo(String yearTo) {
        this.yearTo = yearTo;
    }

    public void setNameSearch(Editable s) {
        nameSearch.set(s.toString());
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public void setIdMake(String idMake) {
        this.idMake = idMake;
    }

    public void changeData(int position) {
        spinModelAdapter.clear();
        spinModelAdapter.addAll(makes.get(position).getModelsCars());
        spinModelAdapter.notifyDataSetChanged();
    }


    public void getUrl() {
        String url = SearchingURL.getURL(idMake, idModel, yearFrom, yearTo);
    }

}
