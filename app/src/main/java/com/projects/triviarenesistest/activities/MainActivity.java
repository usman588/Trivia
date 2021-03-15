package com.projects.triviarenesistest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import com.projects.triviarenesistest.adapters.CategoryGridAdapter;
import com.projects.triviarenesistest.Utills.Constants;
import com.projects.triviarenesistest.models.GridModel;
import com.projects.triviarenesistest.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<GridModel> gridArray = new ArrayList<GridModel>();
    private CategoryGridAdapter categoryGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView_category);

        gridArray = Constants.categoryArray(this);
        categoryGridAdapter = new CategoryGridAdapter(this, R.layout.category_grid_item, gridArray);
        gridView.setAdapter(categoryGridAdapter);






    }
}