package com.delta.sherlock;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Bansal on 12/24/2016.9
 */

public class SecondActivity extends AppCompatActivity implements ClickListener {
    private RecyclerView recyclerView;
    private EpisodeAdapter adapter;
    private List<Episode> episodeList;
    private ImageView back_cover;
    public int DETAIL_REQUEST=1;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(
        android.R.color.transparent));

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back_cover=(ImageView)findViewById(R.id.backdrop);
        episodeList = new ArrayList<>();

        adapter = new EpisodeAdapter(this, episodeList);
        adapter.setClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String detailValue=extras.getString("KeyForSending01");
            if (detailValue != null) {
                Episode a = new Episode("A Study in Pink", "9.1", R.drawable.episode1_1,"On Air : 25 Jul 2010");
                episodeList.add(a);
                a = new Episode("The Blind Banker", "8.1", R.drawable.episode1_2,"On Air : 01 Aug 2010");
                episodeList.add(a);
                a = new Episode("The Great Game", "9.1", R.drawable.episode1_3,"On Air : 08 Aug 2010");
                episodeList.add(a);
                adapter.notifyDataSetChanged();
                collapsingToolbarLayout.setTitle("Series 1");
                back_cover.setImageResource(R.drawable.cover_s1);

           }
            detailValue=extras.getString("KeyForSending02");
            if(detailValue!=null){
                Episode a = new Episode("A Scandal in Belgravia", "9.5", R.drawable.episode2_1,"On Air : 01 Jan 2012");
                episodeList.add(a);
                a = new Episode("The Hounds of Baskerville", "8.5", R.drawable.episode2_2,"On Air : 08 Jan 2012");
                episodeList.add(a);
                a = new Episode("The Reichenbach Fall", "9.7", R.drawable.episode2_3,"On Air : 15 Jan 2012");
                episodeList.add(a);
                adapter.notifyDataSetChanged();
                collapsingToolbarLayout.setTitle("Series 2");
                back_cover.setImageResource(R.drawable.cover_s2);
            }
            detailValue=extras.getString("KeyForSending03");
            if(detailValue!=null) {
                Episode a = new Episode("The Empty Hearse", "9.1", R.drawable.episode3_1,"On Air : 24 Dec 2013");
                episodeList.add(a);
                a = new Episode("The Sign of Three", "9.0", R.drawable.episode3_2,"On Air : 03 Jan 2014");
                episodeList.add(a);
                a = new Episode("His Last Vow", "9.4", R.drawable.episode3_3,"On Air : 05 Jan 2014");
                episodeList.add(a);
                adapter.notifyDataSetChanged();
                collapsingToolbarLayout.setTitle("Series 3");
                back_cover.setImageResource(R.drawable.cover_s3);
            }
            detailValue=extras.getString("KeyForSending04");
            if(detailValue!=null) {
                Episode a = new Episode("The Abominable Bride", "9.1", R.drawable.episode4_1,"On Air : 09 Jan 2016");
                episodeList.add(a);
                adapter.notifyDataSetChanged();
                collapsingToolbarLayout.setTitle("Special");
                back_cover.setImageResource(R.drawable.cover_s4);
            }
        }
    }
    private void initCollapsingToolbar() {

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
                    isShow = true;

                } else if (isShow) {
                    isShow = false;
                }
            }
        });
    }
    @Override
    public void itemClicked(View view, int position) {
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String detailValue=extras.getString("KeyForSending01");
            if (detailValue != null) {
                    if(position==0){
                        Intent i=new Intent(getApplicationContext(),ApiClient.class);
                        i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1665071&plot=full&r=json");
                        i.putExtra("KeyforTrailer","704AblLNlY8");
                        startActivityForResult(i,DETAIL_REQUEST);
                    }
                if(position==1){
                    Intent i=new Intent(this,ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1664529&plot=full&r=json");
                    i.putExtra("KeyforTrailer","y_GGbRkqqF");
                    startActivityForResult(i,DETAIL_REQUEST);
                }
                if(position==2){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1664530&plot=full&r=json");
                    i.putExtra("KeyforTrailer","AviDWKhmVdU");
                    startActivityForResult(i,DETAIL_REQUEST);
                }

            }
             detailValue=extras.getString("KeyForSending02");
            if (detailValue != null) {
                if(position==0){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1942612&plot=full&r=json");
                    i.putExtra("KeyforTrailer","E2MXppyXsUY");
                    startActivityForResult(i,DETAIL_REQUEST);
                }
                if(position==1){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1942613&plot=full&r=json");
                    i.putExtra("KeyforTrailer","uCPwZYkulF8");
                    startActivityForResult(i,DETAIL_REQUEST);
                }
                if(position==2){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt1942614&plot=full&r=json");
                    i.putExtra("KeyforTrailer","MimV42deNMA");
                    startActivityForResult(i,DETAIL_REQUEST);
            }

            }
            detailValue=extras.getString("KeyForSending03");
            if (detailValue != null) {
                if(position==0){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt2189771&plot=full&r=json");
                    i.putExtra("KeyforTrailer","O7cKIjNIPoY");
                    startActivityForResult(i,DETAIL_REQUEST);
                }
                if(position==1){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt2781042&plot=full&r=json");
                    i.putExtra("KeyforTrailer","qtGf6RvjWE4Y");
                    startActivityForResult(i,DETAIL_REQUEST);
                }
                if(position==2){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt2781046&plot=full&r=json");
                    i.putExtra("KeyforTrailer","xhjIsu7n6bI");
                    startActivityForResult(i,DETAIL_REQUEST);
                }

            }
             detailValue=extras.getString("KeyForSending04");
            if (detailValue != null) {
                if(position==0){
                    Intent i=new Intent(getApplicationContext(),ApiClient.class);
                    i.putExtra("KeyforSending","http://www.omdbapi.com/?i=tt3845232&plot=full&r=json");
                    i.putExtra("KeyforTrailer","lAai7yjv6v4");
                    startActivityForResult(i,DETAIL_REQUEST);
                }

            }
            }
    }
}
