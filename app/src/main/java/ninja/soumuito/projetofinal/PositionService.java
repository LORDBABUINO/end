package ninja.soumuito.projetofinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PositionService {

    @GET("/positions.json")
    Call<List<Position>> listPositions();

    @GET("/positions/{id}.json")
    Call<Position> getPosition(@Path("id") String id);
}
