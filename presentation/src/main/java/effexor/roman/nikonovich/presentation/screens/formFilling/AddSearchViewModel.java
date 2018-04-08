package effexor.roman.nikonovich.presentation.screens.formFilling;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.entity.entityChoose.MakeCar;
import effexor.roman.nikonovich.domain.iterators.GetChooseUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import io.reactivex.functions.Consumer;

public class AddSearchViewModel extends BaseViewModel {

    @Inject
    public GetChooseUseCase getChooseUseCase;

    public AddSearchViewModel() {
        App.getAppComponent().inject(this);
        getChooseUseCase
                .getChoose()
                .subscribe(new Consumer<List<MakeCar>>() {
                    @Override
                    public void accept(List<MakeCar> makeCars) throws Exception {
                        List<MakeCar> makes = new ArrayList<>(makeCars);
                        String s = "asd";
                    }
                });
    }


}
