package com.timerlearning.utils;

import com.timerlearning.model.HistoryList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("history.php")
    Call<HistoryList> getAllMessage();

    @GET("history.php")
    Call<HistoryList> getDayMessage(@Query("day") String day);

    @GET("history.php")
    Call<HistoryList> getWeekMessage(@Query("week") String week);

    @GET("history.php")
    Call<HistoryList> getMonthMessage(@Query("month") String month);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> register(@Field("token") String token,
                                @Field("device") String device,
                                @Field("codename") String codename,
                                @Field("model") String model,
                                @Field("os") String os,
                                @Field("product") String product);

    @GET("opcdaq")
    Call<ResponseBody> getFeeding(@Query("tags[]") String _2R1M01,
                                  @Query("tags[]") String _2W1W01,
                                  @Query("tags[]") String _2K1M01,
                                  @Query("tags[]") String _2Z1M01,
                                  @Query("tags[]") String _3R2M01,
                                  @Query("tags[]") String _3W2M01,
                                  @Query("tags[]") String _3K2M01,
                                  @Query("tags[]") String _3Z2M01,
                                  @Query("tags[]") String _4R1M01,
                                  @Query("tags[]") String _4W1W01,
                                  @Query("tags[]") String _4K2M01,
                                  @Query("tags[]") String _4Z1M01,
                                  @Query("tags[]") String _4R2M01,
                                  @Query("tags[]") String _4K3M01,
                                  @Query("tags[]") String _4Z2M01,
                                  @Query("tags[]") String _5R1M01,
                                  @Query("tags[]") String _5W1W01,
                                  @Query("tags[]") String _5K1M01,
                                  @Query("tags[]") String _5Z1M01,
                                  @Query("tags[]") String _5R2M01,
                                  @Query("tags[]") String _5Z2M01,
                                  @Query("tags[]") String _6R1M01,
                                  @Query("tags[]") String _6W1W01,
                                  @Query("tags[]") String _6K1M01,
                                  @Query("tags[]") String _6Z1M01);
}
