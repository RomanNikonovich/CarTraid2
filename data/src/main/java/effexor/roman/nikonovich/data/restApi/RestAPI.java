package effexor.roman.nikonovich.data.restApi;

import java.util.List;

import effexor.roman.nikonovich.data.entity.chooseNet.MakeCarNet;
import effexor.roman.nikonovich.data.entity.chooseNet.ModelNet;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {
    String URL = "search.json?alt=media&token=612ed5c1-54b4-4589-be45-ad49581fcee9";
    String URL_MODELS = "data/MakeCar/{id}/modelsCars?pageSize=50&offset=0";

    @GET(URL)
    Observable<List<MakeCarNet>> loadMakes();

    @GET(URL_MODELS)
    Observable<List<ModelNet>> loadModels(@Path ("id") String id);
}
