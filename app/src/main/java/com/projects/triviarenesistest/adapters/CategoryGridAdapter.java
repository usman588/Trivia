package com.projects.triviarenesistest.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.triviarenesistest.models.GridModel;
import com.projects.triviarenesistest.R;
import com.projects.triviarenesistest.activities.QuizActivity;

import java.util.ArrayList;

public class CategoryGridAdapter extends ArrayAdapter<GridModel> {
    Context context;
    int layoutResourceId;
    ArrayList<GridModel> data = new ArrayList<GridModel>();
    private  Intent intent;

    public CategoryGridAdapter(Context context, int layoutResourceId,
                               ArrayList<GridModel> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.category_txt);
            holder.imageItem = (ImageView) row.findViewById(R.id.category_ic);
            holder.layout = (LinearLayout) row.findViewById(R.id.category_lay);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        GridModel gridModel = data.get(position);
        holder.txtTitle.setText(gridModel.getTitle());
        holder.imageItem.setImageResource(gridModel.getImage());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, QuizActivity.class);
                intent.putExtra("category", gridModel.getCategory());
                intent.putExtra("category_name", gridModel.getTitle());
                categoryAlertDialog();
            }
        });
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
        LinearLayout layout;

    }

    private void categoryAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(Html.fromHtml("<font color='#29337f'>Choose a difficulty level</font>"));
        String[] levels = {"Easy","Medium","Hard"};
        alertDialog.setItems(levels, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                switch (position) {
                    case 0:
                        intent.putExtra("difficulty", "easy");
                        typeAlertDialog();
                        break;
                    case 1:
                        intent.putExtra("difficulty", "medium");
                        typeAlertDialog();
                        break;
                    case 2:
                        intent.putExtra("difficulty", "hard");
                        typeAlertDialog();
                        break;

                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
    private void typeAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(Html.fromHtml("<font color='#29337f'>Choose a type</font>"));
        String[] levels = {"Multiple Choice","True/False"};
        alertDialog.setItems(levels, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                switch (position) {
                    case 0:
                        intent.putExtra("type", "multiple");
                        context.startActivity(intent);

                        break;
                    case 1:
                        intent.putExtra("type", "boolean");
                        context.startActivity(intent);

                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}
