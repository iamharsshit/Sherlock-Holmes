package com.delta.sherlock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int DETAIL_REQUEST=1;


    public MainActivity() {
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCollapsingToolbar();

    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();

                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Sherlock");
                    collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.bg));
                    collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.bg));
                    isShow = true;

                } else if (isShow) {
                    isShow = false;
                }
            }
        });
    }

    public void OnTextClicked(View view){
        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheet3DDialogFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }


    public void Clickable(View view){
        if(view.getId()==R.id.button1 || view.getId()==R.id.image1){
            Intent i=new Intent(this,SecondActivity.class);
            i.putExtra("KeyForSending01","Season1");
            startActivityForResult(i,DETAIL_REQUEST);
        }
        if(view.getId()==R.id.button2 || view.getId()==R.id.image2){
            Intent i=new Intent(this,SecondActivity.class);
            i.putExtra("KeyForSending02","Season2");
            startActivityForResult(i,DETAIL_REQUEST);
        }
        if(view.getId()==R.id.button3 || view.getId()==R.id.image3){
            Intent i=new Intent(this,SecondActivity.class);
            i.putExtra("KeyForSending03","Season3");
            startActivityForResult(i,DETAIL_REQUEST);
        }
        if(view.getId()==R.id.button4 || view.getId()==R.id.image4){
            Intent i=new Intent(this,SecondActivity.class);
            i.putExtra("KeyForSending04","Special");
            startActivityForResult(i,DETAIL_REQUEST);
        }
    }

            }





