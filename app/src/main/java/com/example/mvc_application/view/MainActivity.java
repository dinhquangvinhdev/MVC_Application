package com.example.mvc_application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvc_application.adapter.CountriesAdapter;
import com.example.mvc_application.databinding.ActivityMainBinding;
import com.example.mvc_application.model.Country;
import com.example.mvc_application.networking.CountriesAPI;
import com.example.mvc_application.networking.CountriesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private CountriesAPI api;
    private CountriesAdapter adapter;
    private List<Country> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pullData();
        binding.edtSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().equals("") && !data.isEmpty()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //clone data
                        List<Country> tempData = new ArrayList<>();
                        Log.d("bibi 1", "tempData: " + tempData.size() + " data: " + data.size());
                        for(Country country : data){
                            if(country.getName().getCommon().contains(editable.toString())){
                                tempData.add(country);
                            }
                        }
                        adapter.updateCountries(tempData);
                        Log.d("bibi 2", editable.toString());
                    }
                }else {
                    Log.d("bibi", data.toString());
                    adapter.updateCountries(data);
                }
            }
        });
    }

    private void pullData() {
        binding.progress.setVisibility(View.VISIBLE);

        //testGetCountries1();
        testGetCountries2();
    }

    private void testGetCountries2() {
        Call<List<Country>> call = CountriesService.getInstance().getApi().getCountries2();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                binding.progress.setVisibility(View.VISIBLE);
                updateDataToAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                binding.lvData.setVisibility(View.GONE);
                binding.progress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testGetCountries1() {
        CountriesService.getInstance().getApi().getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> {
                            binding.progress.setVisibility(View.VISIBLE);
                            updateDataToAdapter(result);
                        },
                        (error) -> {
                            binding.lvData.setVisibility(View.GONE);
                            binding.progress.setVisibility(View.GONE);
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        });
    }

    private void updateDataToAdapter(List<Country> result) {
        //clone data
        data = new ArrayList<>(result);
        adapter = new CountriesAdapter(result);
        adapter.addListener(new CountriesAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Country country) {
                Toast.makeText(getApplicationContext() ,country.getName().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.lvData.setAdapter(adapter);
        binding.progress.setVisibility(View.GONE);
        binding.lvData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext() ,"click", Toast.LENGTH_SHORT).show();
    }
}