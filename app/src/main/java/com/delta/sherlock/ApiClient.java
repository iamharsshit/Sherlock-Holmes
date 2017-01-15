package com.delta.sherlock;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Bansal on 12/30/2016.
 */

public class ApiClient  extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,ClickListener {
    private TextView Duration,Plot,ratings,Votes,airDate,Director,Writer;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<Image> imageList;
    ImageView google,fb,wiki;
    private YouTubePlayerView youTubePlayerView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private static String YOUTUBE_VIDEO_CODE=null;

    private String TAG=ApiClient.class.getSimpleName();
    private ProgressDialog progressDialog;
    private String request_url;
    String title,year,duration,plot,rated,votes,release,director,writer,imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_third);
        Duration=(TextView)findViewById(R.id.duration);
        Plot=(TextView)findViewById(R.id.plot);
        ratings=(TextView)findViewById(R.id.ratings);
        Votes=(TextView)findViewById(R.id.votes);
        airDate=(TextView)findViewById(R.id.airDate);
        Director=(TextView)findViewById(R.id.director);
        Writer=(TextView)findViewById(R.id.writer);
        google=(ImageView)findViewById(R.id.search_google);
        wiki=(ImageView)findViewById(R.id.search_wiki);
        fb=(ImageView)findViewById(R.id.search_fb);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse("https://www.google.co.in/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=sherlock%20holmes"));
                startActivity(i);
            }
        });
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse("https://en.wikipedia.org/wiki/Sherlock_(TV_series)"));
                startActivity(i);
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse("https://www.facebook.com/Sherlock.BBCW/"));
                startActivity(i);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        imageList=new ArrayList<>();
        addImage();
        imageAdapter=new ImageAdapter(this,imageList);
        imageAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ApiClient.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(imageAdapter);


        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);

        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(
                android.R.color.transparent));


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
         request_url= extras.getString("KeyforSending");

        if (request_url != null) {

            new RetrieveFeedtask().execute();
        }
        YOUTUBE_VIDEO_CODE=extras.getString("KeyforTrailer");

        }
        youTubePlayerView.initialize(Config.DEVELOPER_KEY,this);

    }

    private void addImage() {
        Image a=new Image(R.drawable.benedict,"Benedict");
        imageList.add(a);
        a=new Image(R.drawable.martin,"Martin Freeman");
        imageList.add(a);
        a=new Image(R.drawable.una,"Una Stubbs");
        imageList.add(a);
        a=new Image(R.drawable.mark,"Mark Gatiss");
        imageList.add(a);
        a=new Image(R.drawable.rupert,"Rupert Graves");
        imageList.add(a);
        a=new Image(R.drawable.amanda_opt,"Amanda");
        imageList.add(a);
        a=new Image(R.drawable.louise,"Louise Brealey");
        imageList.add(a);
        a=new Image(R.drawable.andrew,"Andrew Scott");
        imageList.add(a);
        a=new Image(R.drawable.jonathon,"Jonathan Aris");
        imageList.add(a);
        a=new Image(R.drawable.vinette,"Vinete Robison");
        imageList.add(a);
        a=new Image(R.drawable.tanya,"Tanya Moodie");
        imageList.add(a);
        a=new Image(R.drawable.steven,"Steven Moffat");
        imageList.add(a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
if(!b){
    youTubePlayer.cueVideo(YOUTUBE_VIDEO_CODE);

}
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if(errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        }
        else{
            String errorMessage=String.format(
                    getString(R.string.error_player),errorReason.toString()
            );
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==RECOVERY_DIALOG_REQUEST){
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY,this);}
    }
    private YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void itemClicked(View view, int position) {
        if(position==0){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Benedict_Cumberbatch"));
            startActivity(i);
        }
        if(position==1){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Martin_Freeman"));
            startActivity(i);
        }
        if(position==2){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Una_Stubbs"));
            startActivity(i);
        }
        if(position==3){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Mark_Gattis"));
            startActivity(i);
        }
        if(position==4){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Rupert_Graves"));
            startActivity(i);
        }
        if(position==5){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Amanda_Abbington"));
            startActivity(i);
        }
        if(position==6){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Louise_Brealey"));
            startActivity(i);
        }
        if(position==7){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Andrew_Scott"));
            startActivity(i);
        }
        if(position==8){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Jonathon_aris"));
            startActivity(i);
        }
        if(position==9){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Vinette_Robinson"));
            startActivity(i);
        }
        if(position==10){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Tanya_Moodie"));
            startActivity(i);
        }
        if(position==11){
            Intent i=new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse("https://en.wikipedia.org/wiki/Steven_Moffat"));
            startActivity(i);
        }

    }

    public class RetrieveFeedtask extends AsyncTask<Void,Void,Void> {
        private Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ApiClient.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(request_url);
            Log.e(TAG, "Response from URL: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                     title = jsonObject.getString("Title");
                     year = jsonObject.getString("Year");
                     rated = jsonObject.getString("imdbRating");
                     release = jsonObject.getString("Released");
                     director = jsonObject.getString("Director");
                     plot = jsonObject.getString("Plot");
                     duration=jsonObject.getString("Runtime");
                     votes=jsonObject.getString("imdbVotes");
                     writer=jsonObject.getString("Writer");
                    imgUrl=jsonObject.getString("Poster");}

                 catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            ;
            Duration.setText(duration);
            Plot.setText(plot);
            ratings.setText(rated);
            Votes.setText(votes);
            airDate.setText(release);
            Director.setText(director);
            Writer.setText(writer);



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
                        collapsingToolbar.setTitle(title+" ( "+year+" )");
                        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.bg));
                        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.bg));
                        isShow = true;

                    } else if (isShow) {
                        isShow = false;
                    }
                }
            });
        }
    }



}
