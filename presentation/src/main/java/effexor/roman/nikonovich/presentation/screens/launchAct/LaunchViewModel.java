package effexor.roman.nikonovich.presentation.screens.launchAct;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.widget.Toast;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.data.sharedPref.AppSharedPrefs;
import effexor.roman.nikonovich.domain.iterators.LoadChooseUseCase;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.screens.mainScreen.MainActivity;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class LaunchViewModel extends BaseViewModel {
    public final ObservableBoolean progress = new ObservableBoolean();

    @Inject
    public LoadChooseUseCase loadChooseUseCase;
    @Inject
    public AppSharedPrefs sharedPrefs;

    public LaunchViewModel() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        progress.set(false);
        if(sharedPrefs.isFirstRun()){
            //FIXME delete all toasts
            Toast.makeText(router.getActivity(), "first",
                    Toast.LENGTH_LONG).show();
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
                            sharedPrefs
                                    .saveFirstRun();
                            progress.set(true);
                            runAct();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(router.getActivity(), "Can't load data",
                                    Toast.LENGTH_LONG).show();
                            progress.set(true);
                        }
                    });
        }else {
            progress.set(true);
            runAct();
        }
    }
    private void runAct(){
        Intent runMainAct = new Intent(router.getActivity(), MainActivity.class);
        runMainAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        router
                .getActivity()
                .startActivity(runMainAct);
    }
}
