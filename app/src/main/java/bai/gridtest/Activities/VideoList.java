package bai.gridtest.Activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.Category;
import bai.gridtest.Models.User;
import bai.gridtest.Models.Video;
import bai.gridtest.R;
import bai.gridtest.VideoAdapter;

/**
 * Created by next on 4/16/2017.
 */

public class VideoList extends AppCompatActivity{


    private static Toolbar toolbar;
    private static ListView listView;
    private static EditText vid_name;
    private static Button btnAdd;
    private static User user;
    private static Category category;
    private static DatabaseHelper db;
    private static VideoAdapter adapter;
    private static ArrayList<String> listVideos;
    private static ArrayList<String> listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list);

        db = DatabaseHelper.getInstance(this);
        String u = getIntent().getExtras().getString("user");
        String c = getIntent().getExtras().getString("category");
        user = db.getUser(u);
        category = db.getCategory(c);

        List<Video> videos = db.getAllUserVideos(user.getId(),category.getId());
        listVideos=new ArrayList<String>();
        listNotes=new ArrayList<String>();

        for(Video v : videos){
            listVideos.add(v.getName());
            Log.wtf("heya",db.getNote(user.getId(),v.getId()));
        }

        adapter = new VideoAdapter(VideoList.this,listVideos,user.getId());

        initViews();
    }

    void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView= (ListView)   findViewById(R.id.vid_list);

        btnAdd = (Button) findViewById(R.id.addVid);
        vid_name = (EditText) findViewById(R.id.vname);



        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Video vid = new Video(db.getAllVideos().size(),vid_name.getText().toString());
                if(db.userHasVideo(vid.getId(),user.getId())){
                    Toast.makeText(VideoList.this, "Video already exists in this category.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addVideo(vid);
                    db.addUserVideo(user.getId(),category.getId(),vid.getId());
                    adapter.setValues(user.getId(),category.getId(),vid.getId());
                    listVideos.add(vid_name.getText().toString());
                    adapter.notifyDataSetChanged();
                }*/
                String videoTitle = vid_name.getText().toString();
                if(db.checkVideo(videoTitle)){
                    Video video = db.getVideo(videoTitle);
                    //adapter.setValues(user.getId(),category.getId(),video.getId());
                    if(db.userHasVideo(video.getId(), user.getId())){
                        Toast.makeText(VideoList.this,
                                "Video already exists!" + videoTitle, Toast.LENGTH_SHORT).show();
                    }else{
                        db.addUserVideo(user.getId(),category.getId(),video.getId());
                        listVideos.add(vid_name.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Video video = new Video(db.getAllVideos().size(), videoTitle);
                    //adapter.setValues(user.getId(),category.getId(),video.getId());
                    db.addVideo(video);
                    db.addUserVideo(user.getId(),category.getId(),video.getId());
                    listVideos.add(vid_name.getText().toString());
                    adapter.notifyDataSetChanged();
                }

            }
        });


    }

}
