package com.projects.triviarenesistest.Utills;

import android.content.Context;

import com.projects.triviarenesistest.models.GridModel;
import com.projects.triviarenesistest.R;

import java.util.ArrayList;

public class Constants {


    public static final String QUESTIONS = "Questions";

    public static ArrayList<GridModel> categoryArray(Context ctx)
    {
        ArrayList<GridModel> gridArray = new ArrayList<GridModel>();
        gridArray.add(new GridModel(R.drawable.ic_general_knowledge,"General Knowledge", 9));
        gridArray.add(new GridModel(R.drawable.ic_history_ic,"History", 23));
        gridArray.add(new GridModel(R.drawable.ic_mathematics_ic,"Mathematics",19));
        gridArray.add(new GridModel(R.drawable.ic_art_ic,"Art",25));
        gridArray.add(new GridModel(R.drawable.ic_sports_ic, "Sports",21));
        gridArray.add(new GridModel(R.drawable.ic_geography_ic,"Geography",22));
        gridArray.add(new GridModel(R.drawable.ic_politics_ic,"Politics",24));
        gridArray.add(new GridModel(R.drawable.ic_animals_ic,"Animals", 27));
        gridArray.add(new GridModel(R.drawable.ic_film_ic,"Film", 11));
        gridArray.add(new GridModel(R.drawable.ic_music_ic,"Music",12));
        return gridArray;
    }

}
