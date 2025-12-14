package kaeit.g334.Nokhrin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CalculatorService {

    // GET /calculator/add?a=5&b=7
    @GET("calculator/add")
    Call<String> add(
            @Query("a") String a,
            @Query("b") String b
    );

    @GET("calculator/subtract")
    Call<String> subtract(
            @Query("a") String a,
            @Query("b") String b
    );

    @GET("calculator/multiply")
    Call<String> multiply(
            @Query("a") String a,
            @Query("b") String b
    );

    @GET("calculator/divide")
    Call<String> divide(
            @Query("a") String a,
            @Query("b") String b
    );
}