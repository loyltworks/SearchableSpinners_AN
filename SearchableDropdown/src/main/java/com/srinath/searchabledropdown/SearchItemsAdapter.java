package com.srinath.searchabledropdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchItemsAdapter extends RecyclerView.Adapter<SearchItemsAdapter.ViewHolder> {
    Context context;
    ArrayList<SearchableDropdownModel> SearchItemsList;

    int rowItemTextColor = 0;
    int rowItemTextSize = 0;


    public ItemClickListener itemClickListener;

    public SearchItemsAdapter(Context context, ArrayList<SearchableDropdownModel> SearchItemsList, ItemClickListener itemClickListener){
        this.context = context;
        this.SearchItemsList = SearchItemsList;
        this.itemClickListener = itemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowItem = itemView.findViewById(R.id.rowItems);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(rowItemTextColor!=0){
            holder.rowItem.setTextColor(rowItemTextColor);
        }

        if(rowItemTextSize!=0){
            holder.rowItem.setTextSize(rowItemTextSize);
        }



        holder.rowItem.setText(SearchItemsList.get(position).getName());

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemSelected(SearchItemsList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return SearchItemsList.size();
    }
}
