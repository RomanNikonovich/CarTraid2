package effexor.roman.nikonovich.injection;

import javax.inject.Singleton;

import dagger.Component;
import effexor.roman.nikonovich.presentation.screens.formFilling.AddSearchViewModel;
import effexor.roman.nikonovich.presentation.screens.launchAct.LaunchViewModel;
import effexor.roman.nikonovich.presentation.screens.mainScreen.MainActViewModel;
import effexor.roman.nikonovich.presentation.screens.mainScreen.MainActivity;
import effexor.roman.nikonovich.presentation.screens.searchCar.SearchCarsViewModel;
import effexor.roman.nikonovich.presentation.screens.setting.Setting;
import effexor.roman.nikonovich.presentation.screens.setting.SettingViewModel;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActViewModel viewModel);

    void inject(AddSearchViewModel searchViewModel);

    void inject(LaunchViewModel launchViewModel);

    void inject(MainActivity mainActivity);

    void inject(SearchCarsViewModel searchCarsViewModel);

    void inject(Setting setting);

    void inject(SettingViewModel viewModel);

}
