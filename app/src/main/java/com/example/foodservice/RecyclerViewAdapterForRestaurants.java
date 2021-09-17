package com.example.foodservice;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodservice.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterForRestaurants extends RecyclerView.Adapter<RecyclerViewAdapterForRestaurants.ViewHolder> implements Filterable {
    private static final String TAG = "RestaurantsAdapter";
    private ArrayList<Restaurant> restaurantsList;
    private ArrayList<Restaurant> restaurantsListFull;

    public RecyclerViewAdapterForRestaurants(ArrayList<Restaurant> dataSet) {
        this.restaurantsList = dataSet;
        restaurantsListFull = new ArrayList<>(restaurantsList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textViewCity;
        private final TextView textViewAddress;
        private final ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.nameRestaurant);
            textViewCity = (TextView) v.findViewById(R.id.textViewCity);
            textViewAddress = (TextView) v.findViewById(R.id.textViewAddress);
            this.imageView=(ImageView)v.findViewById(R.id.imageViewRestaurant);

        }
        public TextView getTextView() {
            return textView;
        }
        public TextView getTextViewCity() { return textViewCity;  }
        public TextView getTextViewAddress() { return textViewAddress;  }
        public ImageView getImageView() { return imageView; }
    }

     @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_res, viewGroup, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTextView().setText(restaurantsList.get(position).getName());
        viewHolder.getTextViewCity().setText(restaurantsList.get(position).getCity());
        viewHolder.getTextViewAddress().setText(restaurantsList.get(position).getAddress());
        viewHolder.getImageView().setImageDrawable(viewHolder.getImageView().getContext().getDrawable(restaurantsList.get(position).getImageId()));
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    @Override
    public Filter getFilter() {
        return restaurantFilter;
    }

    private Filter restaurantFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Restaurant> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(restaurantsListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Restaurant item : restaurantsListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) || item.getCity().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            restaurantsList.clear();
            restaurantsList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
