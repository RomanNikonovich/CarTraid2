package effexor.roman.nikonovich.data.restApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import effexor.roman.nikonovich.data.entity.ParseError;
import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import io.reactivex.Observable;

@Singleton
public class RestService {
    private RestAPI restAPI;
    private ErrorTransformers errorTransformers;

    @Inject
    public RestService(RestAPI restAPI, ErrorTransformers errorTransformers) {
        this.restAPI = restAPI;
        this.errorTransformers = errorTransformers;
    }

    public Observable<List<MakeCarNet>> loadMakes(){
        return restAPI
                .loadMakes()
                .compose(errorTransformers.<List<MakeCarNet>, ParseError>parseHttpError());
    }

}