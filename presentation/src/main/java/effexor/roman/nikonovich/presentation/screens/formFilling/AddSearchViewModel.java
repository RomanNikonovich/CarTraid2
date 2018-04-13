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
import effexor.roman.nikonovich.domain.entity.entityChoose.Model;
import effexor.roman.nikonovich.domain.iterators.GetChooseUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.formFilling.spinerAdapter.MakeAdapterSpinner;
import effexor.roman.nikonovich.presentation.screens.formFilling.spinerAdapter.ModelAdapterSpinner;
import io.reactivex.subscribers.DisposableSubscriber;

public class AddSearchViewModel extends BaseViewModel {

    private ObservableField<String> nameSearch = new ObservableField<>("");
    public MakeAdapterSpinner spinMakeAdapter;
    public ModelAdapterSpinner spinModelAdapter;
    private List<MakeCar> makes = new ArrayList<>();
    private List<Model> models = new ArrayList<>();

    @Inject
    Context context;

    @Inject
    public GetChooseUseCase getChooseUseCase;

    public AddSearchViewModel() {
        App.getAppComponent().inject(this);
        spinMakeAdapter = new MakeAdapterSpinner(context, R.layout.spiner_drop_down_item);
        spinModelAdapter = new ModelAdapterSpinner(context, R.layout.spiner_drop_down_item, models);
        compositeDisposable
                .add(getChooseUseCase
                        .getChoose()
                        .subscribeWith(new DisposableSubscriber<List<MakeCar>>() {
                            @Override
                            public void onNext(List<MakeCar> makeCars) {
                                spinMakeAdapter.addAll(makeCars);
                                models.addAll(makeCars.get(0).getModelsCars());
                                spinModelAdapter.notifyDataSetChanged();
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


    public void setNameSearch(Editable s) {
        nameSearch.set(s.toString());
    }

    public void changeData(int position) {

        models.addAll(makes.get(position).getModelsCars());
        spinModelAdapter.notifyDataSetChanged();
    }

}
