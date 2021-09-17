package com.example.foodservice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import com.example.foodservice.model.Franchise;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterForFranchises extends RecyclerView.Adapter<RecyclerViewAdapterForFranchises.ViewHolder> implements Filterable {
    private static final String TAG = "FranchisesAdapter";
    private ArrayList<Franchise> franchisesList;
    private ArrayList<Franchise> franchisesListFull;

    public RecyclerViewAdapterForFranchises(ArrayList<Franchise> dataSet) {
        this.franchisesList = dataSet;
        franchisesListFull = new ArrayList<>(franchisesList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textViewDesc;
        private final ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textViewName);
            textViewDesc = (TextView) v.findViewById(R.id.textViewDesc);
            this.imageView=(ImageView)v.findViewById(R.id.imageViewFranchise);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String franchiseId;
                    int pos = getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION){
                        franchiseId = String.valueOf(franchisesList.get(pos).getId());
                        Intent intent = new Intent(view.getContext(), ResActivity.class);
                        Bundle b = new Bundle();
                        b.putString("franchiseId", franchiseId);
                        intent.putExtras(b);
                        view.getContext().startActivity(intent);

                    }

                }
            });

        }
        public TextView getTextView() {
            return textView;
        }
        public TextView getTextViewDesc() { return textViewDesc;  }
        public ImageView getImageView() { return imageView; }

    }

     @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTextView().setText(franchisesList.get(position).getName());
        viewHolder.getTextViewDesc().setText(franchisesList.get(position).getDescription());
        viewHolder.getImageView().setImageDrawable(viewHolder.getImageView().getContext().getDrawable(franchisesList.get(position).getImageId()));
    }

    @Override
    public int getItemCount() {
        return franchisesList.size();
    }

    @Override
    public Filter getFilter() {
        return franchiseFilter;
    }

    private Filter franchiseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Franchise> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(franchisesListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Franchise item : franchisesListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) || item.getDescription().toLowerCase().contains(filterPattern)) {
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
            franchisesList.clear();
            franchisesList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
