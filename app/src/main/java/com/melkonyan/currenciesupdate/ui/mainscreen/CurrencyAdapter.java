package com.melkonyan.currenciesupdate.ui.mainscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.melkonyan.currenciesupdate.R;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

  public CurrencyAdapter() {
  }

  @NonNull @Override public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
    return new CurrencyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }
}
