package effexor.roman.nikonovich.injection;

import javax.inject.Singleton;

import dagger.Component;
import effexor.roman.nikonovich.huckster.presentation.screens.mainScreen.MainActViewModel;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActViewModel viewModel);

}
