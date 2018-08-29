package com.melkonyan.currenciesupdate.ui.api.data;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

@AutoValue
public abstract class CurrencyRate implements Parcelable {
  @SerializedName("base")
  abstract String base();

  @SerializedName("date")
  abstract String date();

  @SerializedName("rates")
  abstract Map<String, String> rates();

  public static TypeAdapter<CurrencyRate> typeAdapter(Gson gson) {
    return new AutoValue_CurrencyRate.GsonTypeAdapter(gson);
  }
}
