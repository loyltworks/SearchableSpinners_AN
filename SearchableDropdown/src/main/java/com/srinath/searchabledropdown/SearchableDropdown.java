package com.srinath.searchabledropdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchableDropdown extends RelativeLayout {

    RelativeLayout searchableDropdown;


    TextView selectedItemTextView;

    ItemClickListener itemClickListener;


    private ArrayList<SearchableDropdownModel> searchItemsList = new ArrayList<>();




    public SearchableDropdown(Context context) {
        super(context);
    }

    public SearchableDropdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SearchableDropdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchableDropdown(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_searchable_dropdown, this);

        searchableDropdown = findViewById(R.id.searchableDropdown);
        selectedItemTextView = findViewById(R.id.selectedItemTextView);
        ImageView searchableDropdownIcon = findViewById(R.id.searchableDropdownIcon);






        searchableDropdown.setOnClickListener(view -> {

            SearchDialog.getInstance().showDialog(context, selectedItem -> {
                selectedItemTextView.setText(selectedItem.getName());
                itemClickListener.onItemSelected(selectedItem);
            });
            SearchDialog.getInstance().setSearchItemsList(context,searchItemsList);


        });

        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SearchableDropdown,0,0);



            if(typedArray.getDrawable(R.styleable.SearchableDropdown_Dropdown_background)!=null){
                searchableDropdown.setBackground(typedArray.getDrawable(R.styleable.SearchableDropdown_Dropdown_background));
            }
            if(typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_TextSize,0)!=0){
                selectedItemTextView.setTextSize(typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_TextSize,0));

            }
            if(typedArray.getColor(R.styleable.SearchableDropdown_Dropdown_TextColor,0)!=0){
                selectedItemTextView.setTextColor(typedArray.getColor(R.styleable.SearchableDropdown_Dropdown_TextColor,0));

            }
            if(typedArray.getResourceId(R.styleable.SearchableDropdown_Dropdown_Icon,0)!=0){
                searchableDropdownIcon.setImageResource(typedArray.getResourceId(R.styleable.SearchableDropdown_Dropdown_Icon,0));
                searchableDropdownIcon.requestLayout();

            }
            if(typedArray.getColor(R.styleable.SearchableDropdown_Dropdown_IconColor,0)!=0){
                searchableDropdownIcon.setColorFilter(typedArray.getColor(R.styleable.SearchableDropdown_Dropdown_IconColor,0));
                searchableDropdownIcon.requestLayout();

            }
            if(typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_IconHeight,0)!=0){
                searchableDropdownIcon.getLayoutParams().height = typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_IconHeight,0);
                searchableDropdownIcon.requestLayout();

            }
            if(typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_IconWidth,0) != 0){
                searchableDropdownIcon.getLayoutParams().width = typedArray.getInt(R.styleable.SearchableDropdown_Dropdown_IconWidth,0);
                searchableDropdownIcon.requestLayout();
            }






            SearchDialog.getInstance().searchDialogBackground = typedArray.getDrawable(R.styleable.SearchableDropdown_SearchDialog_Background);

            SearchDialog.getInstance().searchLayoutBackground = typedArray.getDrawable(R.styleable.SearchableDropdown_SearchLayout_background);

            SearchDialog.getInstance().searchtextsize = typedArray.getInt(R.styleable.SearchableDropdown_SearchLayout_TextSize,0);
            SearchDialog.getInstance().searchtextColor = typedArray.getColor(R.styleable.SearchableDropdown_SearchLayout_TextColor,0);

            SearchDialog.getInstance().searchIconDrawable = typedArray.getResourceId(R.styleable.SearchableDropdown_SearchLayout_Icon,0);
            SearchDialog.getInstance().searchIconHeight = typedArray.getInt(R.styleable.SearchableDropdown_SearchLayout_IconHeight,0);
            SearchDialog.getInstance().searchIconWidth = typedArray.getInt(R.styleable.SearchableDropdown_SearchLayout_IconWidth,0);
            SearchDialog.getInstance().searchIconColor = typedArray.getColor(R.styleable.SearchableDropdown_SearchLayout_IconColor,0);

            SearchDialog.getInstance().clearSearchIconDrawable = typedArray.getResourceId(R.styleable.SearchableDropdown_SearchLayout_ClearIcon,0);
            SearchDialog.getInstance().clearSearchIconHeight = typedArray.getInt(R.styleable.SearchableDropdown_SearchLayout_ClearIconHeight,0);
            SearchDialog.getInstance().clearSearchIconWidth = typedArray.getInt(R.styleable.SearchableDropdown_SearchLayout_ClearIconWidth,0);
            SearchDialog.getInstance().clearSearchIconColor = typedArray.getColor(R.styleable.SearchableDropdown_SearchLayout_ClearIconColor,0);

            SearchDialog.getInstance().closeButtonBackground = typedArray.getDrawable(R.styleable.SearchableDropdown_CloseButton_background);
            SearchDialog.getInstance().closeButtonTextColor = typedArray.getColor(R.styleable.SearchableDropdown_CloseButton_TextColor,0);
            SearchDialog.getInstance().closeButtonTextSize = typedArray.getInt(R.styleable.SearchableDropdown_CloseButton_TextSize,0);

            SearchDialog.getInstance().searchItemsRVBackground = typedArray.getDrawable(R.styleable.SearchableDropdown_SearchList_background);
            SearchDialog.getInstance().searchItemsRVTextColor = typedArray.getColor(R.styleable.SearchableDropdown_SearchList_TextColor,0);
            SearchDialog.getInstance().searchItemsRVTextSize = typedArray.getInt(R.styleable.SearchableDropdown_SearchList_TextSize,0);

            typedArray.recycle();
        }






    }

    public void setSearchItemsList(ArrayList<SearchableDropdownModel> searchItemsList,int alreadySelectedId,ItemClickListener itemClickListener) {
        this.searchItemsList = searchItemsList;
        this.itemClickListener = itemClickListener;
        if(alreadySelectedId == 0){
            setSelection(0);
        }else{
            for(int i=0;i<searchItemsList.size();i++){
                if(searchItemsList.get(i).id == alreadySelectedId){
                    setSelection(i);
                }
            }
        }

    }


    public void setSelection(int position){
        selectedItemTextView.setText(searchItemsList.get(position).getName());
        itemClickListener.onItemSelected(searchItemsList.get(position));
    }

}


