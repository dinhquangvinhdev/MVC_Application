package com.example.mvc_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc_application.databinding.ItemCountryBinding;
import com.example.mvc_application.model.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder> {

    private onItemClickListener listener;
    private List<Country> countries;

    public CountriesAdapter(List<Country> countries) {
        this.countries = countries;
    }

    public void addListener(onItemClickListener listener){
        this.listener = listener;
    }

    public void updateCountries(List<Country> arr){
        countries.clear();
        countries.addAll(arr);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemCountryBinding binding;

        public MyViewHolder(@NonNull ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Country country , onItemClickListener listener){
            binding.tvTitle.setText(country.getName().getCommon());
            binding.tvBody.setText(country.toString());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(country);
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(Country country);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Country tempCountry = countries.get(position);
        holder.onBind(tempCountry, this.listener);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


}
