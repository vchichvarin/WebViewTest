package com.example.vchichvarin.webviewlib.locationcheck;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryRetrofit {

    private static CountryRetrofit mInstance;
    private static final String BASE_URL = "http://api.ipstack.com";
    private Retrofit mRetrofit;

    private CountryRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static CountryRetrofit getInstance() {
        if (mInstance == null) {
            mInstance = new CountryRetrofit();
        }
        return mInstance;
    }

    public Api getJSONApi() {
        return mRetrofit.create(Api.class);
    }

    /*private static Retrofit getRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(Api.DOMAIN);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.client(client);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        retrofitBuilder.client(okHttpClientBuilder.build());

        return retrofitBuilder.build();
    }

    public static Api initRetrofit() {
        return getRetrofit().create(Api.class);
    }

    public static boolean requestRetrofit(String IP) {

        mApi.getCountryByIP(IP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Country>() {
                    @Override
                    public void accept(Country country) throws Exception {
                        strana = Country.getCountry().toString();
                        if (strana == "Russian federation") { isFromRussia = true; }
                        else isFromRussia = false;
                    }
                });
    return isFromRussia;
    }   */

}
