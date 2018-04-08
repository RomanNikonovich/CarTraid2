package effexor.roman.nikonovich.presentation.screens.mainScreen;


import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.domain.iterators.LoadChooseUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.formFilling.AddNewSearch;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

import static android.content.Context.MODE_PRIVATE;

public class MainActViewModel extends BaseViewModel {

    private SharedPreferences pref;

    @Inject
    public LoadChooseUseCase loadChooseUseCase;

    public void addSearch() {
        router
                .getActivity()
                .startActivity(new Intent(router.getActivity(), AddNewSearch.class));
    }

    public MainActViewModel() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        pref = router.getActivity().getSharedPreferences("first run", MODE_PRIVATE);
        if (pref.getBoolean("first", true)) {
            Toast.makeText(router.getActivity(), "first",
                    Toast.LENGTH_LONG).show();
            loadChoose();
        }
        pref
                .edit()
                .putBoolean("first", false)
                .apply();
    }

    private void loadChoose() {
        loadChooseUseCase
                .loadChoose()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(router.getActivity(), "loaded",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(router.getActivity(), "Error",
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
}
