package com.melkonyan.currenciesupdate.ui.api.network;

import com.google.gson.Gson;
import com.melkonyan.currenciesupdate.BuildConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
  public static final int TIMEOUT = 40;

  public static CurrencyAPI create() throws IllegalArgumentException {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    // TODO перед релизом выставлдять на NONE
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    HttpLoggingInterceptor headerInterception = new HttpLoggingInterceptor();
    headerInterception.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client;
    if (BuildConfig.DEBUG) {
      client = new OkHttpClient.Builder()
          .addInterceptor(logging)
          .addInterceptor(getAuthInterceptor())
          .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
          .readTimeout(TIMEOUT, TimeUnit.SECONDS)
          .addNetworkInterceptor(new StethoInterceptor())
          .cookieJar(NetworkUtil.getCookieJar())
          .build();
    } else {
      client = new OkHttpClient.Builder()
          .addInterceptor(getAuthInterceptor())
          .addInterceptor(logging)
          .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
          .readTimeout(TIMEOUT, TimeUnit.SECONDS)
          .addNetworkInterceptor(new StethoInterceptor())
          .cookieJar(NetworkUtil.getCookieJar())
          .build();
    }

    Gson gson = NetworkUtil.createGson();

    try {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(CurrencyAPI.BASE_URL)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();
      return retrofit.create(CurrencyAPI.class);
    } catch (IllegalArgumentException error) {
      throw error;
    }
  }

  public static CurrencyAPI refreshRetrofitService() {
    return create();
  }
}
