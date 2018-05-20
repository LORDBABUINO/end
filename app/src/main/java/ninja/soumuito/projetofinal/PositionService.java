package ninja.soumuito.projetofinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PositionService {

    @GET("https://jobs.github.com/positions.json")
    Call<List<Position>> listPositions();

    @GET("https://jobs.github.com/positions.json")
    Call<List<Position>> getPosition();
}
