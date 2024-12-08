package com.nguyenngochaianh.btl.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenngochaianh.btl.databinding.ItemCarCareInfoBinding;
import com.nguyenngochaianh.btl.model.CarCare;

import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class CarCareAdapter extends RecyclerView.Adapter<CarCareViewHolder> {

    private List<CarCare> carCares;

    public CarCareAdapter(List<CarCare> carCares) {
        this.carCares = carCares;
    }

    public void setDataSource(List<CarCare> carCares) {
        this.carCares = carCares;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarCareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarCareInfoBinding binding = ItemCarCareInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarCareViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarCareViewHolder holder, int position) {
        if (position < carCares.size()) {
            holder.bind(carCares.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return carCares != null ? carCares.size() : 0;
    }
}
