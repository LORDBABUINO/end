package ninja.soumuito.projetofinal.job;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobController {

    static final String BASE_URL = "https://jobs.github.com/";

    public static Call<List<Job>> list(){
        return createService().listJobs();
    }

    public static Call <Job> getJob(String id){

        return createService().getJob(id);
    }

    private static JobService createService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(JobService.class);
    }
}
