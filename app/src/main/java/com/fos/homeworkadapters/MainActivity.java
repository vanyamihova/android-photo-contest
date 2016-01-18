package com.fos.homeworkadapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fos.homeworkadapters.adapter.RecyclerViewAdapter;
import com.fos.homeworkadapters.model.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ArticleModel> allModels = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // generate dummy data
        generateData();

        // initialize adapter for recycler view
        adapter = new RecyclerViewAdapter(allModels);



        // initialize RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // set linear layout manager for recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // set adapter for recycler view
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5000) {
            if(resultCode == Activity.RESULT_OK){
                int result = data.getIntExtra("countComment", Integer.MIN_VALUE);
                int itemPosition = data.getIntExtra("itemPosition", Integer.MIN_VALUE);

                ArticleModel article = allModels.get(itemPosition);
                article.setCountComments(article.getCountComments() + result);
                allModels.set(itemPosition, article);

                adapter.notifyItemChanged(itemPosition);
            }
        }
    }

    private void generateData() {
        allModels.add(new ArticleModel("Title 1", R.drawable.image_1, 0, 0));
        allModels.add(new ArticleModel("Title 222", R.drawable.image_2, 876, 0));
        allModels.add(new ArticleModel("Title 3", R.drawable.image_3, 132, 5));
        allModels.add(new ArticleModel("Title 44", R.drawable.image_4, 3, 32));
        allModels.add(new ArticleModel("Title 5555", R.drawable.image_5, 1, 0));
        allModels.add(new ArticleModel("Title 6666", R.drawable.image_6, 0, 432));
    }

}
