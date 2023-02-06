package com.example.mvc_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc_application.R;
import com.example.mvc_application.databinding.ItemCountryBinding;
import com.example.mvc_application.model.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder> {

    private onItemClickListener listener;
    private List<Country> countries;

    public CountriesAdapter(onItemClickListener listener, List<Country> countries) {
        this.listener = listener;
        this.countries = countries;
    }

    private void addListener(onItemClickListener listener){
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
            binding.tvTitle.setText(country.getName().getCommom());
            binding.tvBody.setText(country.toString());
            binding.getRoot().setOnClickListener(listener.onItemClick(country));
        }
    }

    public interface onItemClickListener {
        View.OnClickListener onItemClick(Country country);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(countries.get(position), this.listener);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


}
