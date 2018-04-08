package effexor.roman.nikonovich.data.restApi;

import java.util.List;

import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestAPI {
    String URL = "data/MakeCar?pageSize=100&sortBy=makeCar%20asc";

    @GET(URL)
    Observable<List<MakeCarNet>> loadMakes();
}
