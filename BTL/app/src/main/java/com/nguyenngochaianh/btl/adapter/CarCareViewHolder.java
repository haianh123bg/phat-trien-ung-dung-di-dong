package com.nguyenngochaianh.btl.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.nguyenngochaianh.btl.R;
import com.nguyenngochaianh.btl.databinding.ItemCarCareInfoBinding;
import com.nguyenngochaianh.btl.model.CarCare;

public class CarCareViewHolder extends RecyclerView.ViewHolder {

    private final ItemCarCareInfoBinding binding;

    public CarCareViewHolder(ItemCarCareInfoBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CarCare carCare) {
        Context context = binding.getRoot().getContext();
        binding.tvCarId.setText(context.getString(R.string.value_car_id, carCare.getCarId()));

        String[] service = carCare.getRegistrationService().split(",");
        StringBuilder serviceString = new StringBuilder(": Đang ");
        for (String s : service) {
            switch (s) {
                case "WASH":
                    serviceString.append(context.getString(R.string.service_wash).toLowerCase());
                    break;
                case "OIL":
                    serviceString.append(context.getString(R.string.service_oil).toLowerCase());
                    break;
                case "HOLD":
                    serviceString.append(context.getString(R.string.service_hold).toLowerCase());
                    break;
            }
            serviceString.append(", ");
        }
        serviceString.delete(serviceString.length() - 2, serviceString.length());
        binding.tvStatus.setText(serviceString.toString());
    }
}
