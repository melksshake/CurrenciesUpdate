package com.melkonyan.currenciesupdate.ui.api;

import com.melkonyan.currenciesupdate.ui.api.data.CurrencyRate;
import retrofit2.http.GET;
import rx.Observable;

public interface CurrnecyApi {
  public static final String BASE_URL = "https://revolut.duckdns.org";

  @GET(BASE_URL + "/latest?base=EUR")
  Observable<CurrencyRate> getCurrent();
}
