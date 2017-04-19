package bai.gridtest.Activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    private static ArrayAdapter<String> mAdapter;
    private static ArrayList<String> listVideos;
    int i = 0;

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


        for(Video v : videos){
            listVideos.add(v.getName());
        }

        adapter = new VideoAdapter(VideoList.this,listVideos);

        initViews();
    }

    void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView= (ListView)   findViewById(R.id.vid_list);

        btnAdd = (Button) findViewById(R.id.addVid);
        vid_name = (EditText) findViewById(R.id.vname);



        mAdapter = new ArrayAdapter<String>(VideoList.this,
                R.layout.video_item,R.id.vid_title,
                listVideos);

        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Video vid = new Video(db.getAllVideos().size(),vid_name.getText().toString());
                db.addVideo(vid);
                db.addUserVideo(user.getId(),category.getId(),vid.getId());
                listVideos.add(vid_name.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });


    }

}
