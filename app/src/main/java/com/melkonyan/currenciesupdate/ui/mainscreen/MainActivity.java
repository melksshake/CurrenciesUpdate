package com.melkonyan.currenciesupdate.ui.mainscreen;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.melkonyan.currenciesupdate.R;
import com.melkonyan.currenciesupdate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    binding.recycler.setHasFixedSize(true);
  }
}
