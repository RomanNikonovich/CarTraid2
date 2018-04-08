package effexor.roman.nikonovich.presentation.base;


import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<R extends BaseRouter> extends ViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Nullable
    protected R router;

    public void attachRouter(R router) {
        this.router = router;
    }

    public void detachRouter() {
        router = null;
    }

    public void onCreate() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
