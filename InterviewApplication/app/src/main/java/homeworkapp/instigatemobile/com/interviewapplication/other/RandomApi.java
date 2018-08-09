package homeworkapp.instigatemobile.com.interviewapplication.other;

import homeworkapp.instigatemobile.com.interviewapplication.models.UserRecyclers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomApi {
    @GET("api/")
    Call<UserRecyclers> randomUsers(@Query("results") Integer results);
}
