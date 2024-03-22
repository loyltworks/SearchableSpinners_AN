package com.srinath.searchabledropdown;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchDialog {
    Dialog dialog;

    public static SearchDialog instance = null;

    private SearchDialog(){

    }

    public static synchronized SearchDialog getInstance(){
        if(instance == null){
            instance = new SearchDialog();
        }
        return instance;
    }


    RelativeLayout searchLayout;
    RecyclerView searchItemsRV;

    AppCompatButton closeButton;
    TextView noDataFound;


    SearchItemsAdapter searchItemsAdapter;

    RelativeLayout searchDialogLayout;

    ItemClickListener itemClickListener;

    Drawable searchDialogBackground;



    Drawable searchLayoutBackground;
    ImageView searchIcon;
    EditText searchText;
    ImageView clearSearch;

    int searchtextsize = 0;
    int searchtextColor = 0;

    int searchIconDrawable = 0;
    int searchIconHeight = 0;
    int searchIconWidth = 0;
    int searchIconColor = 0;

    int clearSearchIconDrawable = 0;
    int clearSearchIconHeight = 0;
    int clearSearchIconWidth = 0;
    int clearSearchIconColor = 0;

    Drawable closeButtonBackground;

    int closeButtonTextColor = 0;

    int closeButtonTextSize = 0;

    Drawable searchItemsRVBackground;

    int searchItemsRVTextColor = 0;

    int searchItemsRVTextSize = 0;

    public void showDialog(Context context,ItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setContentView(R.layout.layout_search_dialog);
        dialog.show();

        closeButton = dialog.findViewById(R.id.closeButton);
        searchText = dialog.findViewById(R.id.searchText);
        searchItemsRV = dialog.findViewById(R.id.searchItemRV);
        noDataFound = dialog.findViewById(R.id.noDataFound);
        clearSearch = dialog.findViewById(R.id.clearSearchIcon);
        searchIcon = dialog.findViewById(R.id.searchIcon);
        searchDialogLayout = dialog.findViewById(R.id.searchDialogLayout);
        searchLayout = dialog.findViewById(R.id.searchLayout);




        clearSearch.setOnClickListener(view -> {searchText.getText().clear();});

        closeButton.setOnClickListener(view -> dialog.hide());

        if(searchDialogBackground != null){
            searchDialogLayout.setBackground(searchDialogBackground);
        }

        if(searchLayoutBackground != null){
            searchLayout.setBackground(searchLayoutBackground);
        }
        if(searchtextsize != 0){
            searchText.setTextSize(searchtextsize);
        }
        if(searchtextColor != 0){
            searchText.setTextColor(searchtextColor);
        }
        if(searchIconDrawable != 0){
            searchIcon.setImageResource(searchIconDrawable);
        }
        if(searchIconHeight != 0){
            searchIcon.getLayoutParams().height = searchIconHeight;

        }
        if(searchIconWidth != 0){
            searchIcon.getLayoutParams().width = searchIconWidth;
        }
        if(searchIconColor != 0){
            searchIcon.setColorFilter(searchIconColor);

        }
        if(clearSearchIconDrawable != 0){
            clearSearch.setImageResource(clearSearchIconDrawable);

        }
        if(clearSearchIconHeight != 0){
            clearSearch.getLayoutParams().height = clearSearchIconHeight;
        }
        if(clearSearchIconWidth != 0){
            clearSearch.getLayoutParams().width = clearSearchIconWidth;

        }
        if(clearSearchIconColor != 0){
            clearSearch.setColorFilter(clearSearchIconColor);

        }
        if(closeButtonBackground != null){
            closeButton.setBackground(closeButtonBackground);

        }
        if(closeButtonTextColor != 0){
            closeButton.setTextColor(closeButtonTextColor);

        }
        if(closeButtonTextSize != 0){
            closeButton.setTextSize(closeButtonTextSize);

        }
        if(searchItemsRVBackground != null){
            searchItemsRV.setBackground(searchItemsRVBackground);
        }












    }

    public  void setSearchItemsList(Context context, ArrayList<SearchableDropdownModel> searchItemsList){

        if(dialog!=null){
            ArrayList<SearchableDropdownModel> filteredList = new ArrayList<>(searchItemsList);

            searchItemsAdapter = new SearchItemsAdapter(context, filteredList, selectedItem -> {
                itemClickListener.onItemSelected(selectedItem);
                dialog.dismiss();
            });

            if(searchItemsRVTextColor != 0){
                searchItemsAdapter.rowItemTextColor = searchItemsRVTextColor;

            } if(searchItemsRVTextSize != 0){
                searchItemsAdapter.rowItemTextSize = searchItemsRVTextSize;
            }


            searchItemsRV.setAdapter(searchItemsAdapter);



            searchText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    filteredList.clear();

                    for(int j = 0; j < searchItemsList.size();j++)
                    {
                        if(searchItemsList.get(j).getName().toLowerCase(Locale.ROOT).contains(charSequence.toString().toLowerCase(Locale.ROOT))){
                            filteredList.add(searchItemsList.get(j));

                        }
                    }
                    if(filteredList.isEmpty()){
                        noDataFound.setVisibility(View.VISIBLE);
                        searchItemsRV.setVisibility(View.GONE);
                    }else{
                        noDataFound.setVisibility(View.GONE);
                        searchItemsRV.setVisibility(View.VISIBLE);
                    }

                    searchItemsAdapter.notifyDataSetChanged();

                    if(charSequence.length()>0){
                        clearSearch.setVisibility(View.VISIBLE);
                    }else{
                        clearSearch.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }









}
