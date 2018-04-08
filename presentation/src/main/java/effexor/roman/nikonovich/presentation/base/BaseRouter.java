package effexor.roman.nikonovich.presentation.base;

import android.app.Activity;

public abstract class BaseRouter {

    private Activity activity;

    public BaseRouter(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
