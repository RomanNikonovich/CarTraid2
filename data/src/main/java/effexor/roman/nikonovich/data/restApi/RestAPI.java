package effexor.roman.nikonovich.data.restApi;

import java.util.List;

import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestAPI {

    @GET("data/MakeCar?pageSize=100&sortBy=makeCarR%20asc")
    Observable<List<MakeCarNet>> loadMakes();
}
