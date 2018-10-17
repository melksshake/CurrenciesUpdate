package com.melkonyan.currenciesupdate.ui.api.network;

import com.melkonyan.currenciesupdate.ui.api.data.CurrencyRate;
import retrofit2.http.GET;
import rx.Observable;

public interface CurrencyAPI {
  String BASE_URL = "https://revolut.duckdns.org";

  @GET(BASE_URL + "/latest?base=EUR")
  Observable<CurrencyRate> getCurrent();
}
