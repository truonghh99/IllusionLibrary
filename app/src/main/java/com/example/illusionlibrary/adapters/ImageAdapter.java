package com.example.illusionlibrary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.illusionlibrary.databinding.ItemImageBinding;
import com.example.illusionlibrary.models.Image;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private static final String TAG = "CurrentFoodAdapter";
    private Context context;
    public static List<Image> images;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemImageBinding itemImageBinding = ItemImageBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemImageBinding);
    }

    public ImageAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Image image = images.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void replaceAll(List<Image> filteredProducts) {
        images = filteredProducts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView ivImage;

        public ViewHolder(@NonNull ItemImageBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            tvName = itemProductBinding.tvName;
            ivImage = itemProductBinding.ivImage;
        }

        public void bind(final Image image) {
            tvName.setText(image.getImageName());
        }
    }

}