package ninja.soumuito.projetofinal.job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JobService {

    @GET("/positions.json")
    Call<List<Job>> listJobs();

    @GET("/positions/{id}.json")
    Call<Job> getJob(@Path("id") String id);
}
