package effexor.roman.nikonovich.presentation.screens.contactDeveloper;

import android.arch.lifecycle.ViewModelProviders;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.databinding.ActivityContactDeveloperBinding;
import effexor.roman.nikonovich.presentation.base.BaseActivity;

public class ContactDeveloper extends
        BaseActivity<ActivityContactDeveloperBinding, ContactDevViewModel, ContactRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_contact_developer;
    }

    @Override
    public ContactDevViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(ContactDevViewModel.class);
    }

    @Override
    public ContactRouter provideRouter() {
        return new ContactRouter(this);
    }

}
