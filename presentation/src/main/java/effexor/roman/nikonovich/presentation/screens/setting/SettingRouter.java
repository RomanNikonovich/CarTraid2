package effexor.roman.nikonovich.presentation.screens.setting;

import android.app.Activity;

import effexor.roman.nikonovich.presentation.base.BaseRouter;


class SettingRouter extends BaseRouter {
    SettingRouter(Activity activity) {
        super(activity);
    }

    @Override
    public void back() {
        this.getActivity().onBackPressed();
    }
}
