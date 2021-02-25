package com.example.vchichvarin.webviewlib.locationcheck;

import com.example.vchichvarin.webviewlib.locationcheck.data.Country;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.*;

public interface Api {

    @GET("/{IP}")
    public Call<Country> getCountryByIP(@Path("IP") String ip, @Query("access_key") String access_key);

}
