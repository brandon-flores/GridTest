package bai.gridtest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import bai.gridtest.Activities.MainActivity;
import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.Category;
import bai.gridtest.Models.User;
import bai.gridtest.Models.Video;

/**
 * Created by next on 4/19/2017.
 */

public class VideoAdapter extends BaseAdapter implements ListAdapter{


    private Context mContext;

    DatabaseHelper db = DatabaseHelper.getInstance(mContext);
    private final List<String> v_title;
    //private final List<String> v_note;
    EditText v_name;
    TextView note;
    EditText v_notes;
    int position;
    int user_id , category_id, video_id;


    public VideoAdapter(Context c, List<String> v_title ,int user_id) {
        mContext = c;
        this.v_title = v_title;
        this.user_id = user_id;
        //this.v_note = v_note;
    }
//    public void setValues(int user_id,int category_id,int video_id){
//        Log.wtf("YAY","YAYAYAYAY");
//        this.user_id = user_id;
//        this.category_id = category_id;
//        this.video_id = video_id;
//    }

    @Override
    public int getCount() {
        return v_title.size();
    }

    @Override
    public Object getItem(int position) {
        return v_title.get(position);
}

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View list;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            list = new View(mContext);
            list  = inflater.inflate(R.layout.video_item, null);

            TextView textView = (TextView) list.findViewById(R.id.vid_title);
            textView.setText(v_title.get(position));
            note = (TextView) list.findViewById(R.id.notes);


            ImageButton edit = (ImageButton) list.findViewById(R.id.edit);
            ImageButton delete = (ImageButton) list.findViewById(R.id.delete);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selected = getItem(position).toString();
                   // note = (TextView)v.findViewById(R.id.notes);
                    Video video = db.getVideo(selected);
                    video_id = video.getId();
                    note.setText(db.getNote(user_id,video_id));
                    Log.wtf("selected",selected);
                    Toast.makeText(mContext,"EDIT~",Toast.LENGTH_SHORT).show();
                    Log.wtf("editing","edited");
                    showDialog();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selected = getItem(position).toString();
                    Video video = db.getVideo(selected);

                    db.deleteUserVideo(user_id,video.getId());
                    v_title.remove(0);
                    VideoAdapter.this.notifyDataSetChanged();
                }
            });

        } else {
            list = (View) convertView;
        }



        return list;
    }



    private void showDialog(){
        //final Button image = new Button(getActivity());
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.edit_video);
        dialog.setCancelable(true);

        v_name = (EditText) dialog.findViewById(R.id.new_vid_name);
        v_name.setText(v_title.get(position));
        v_notes = (EditText) dialog.findViewById(R.id.notes);

        Button ok = (Button)dialog.findViewById(R.id.okay) ;
        Button cancel = (Button)dialog.findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("userrrrrrr",user_id + " " + video_id + " " + v_notes.getText().toString());
                db.addUserVideoNotes(user_id,video_id,v_notes.getText().toString());
                Log.wtf("usernote",db.getNote(user_id,video_id) + "ll");
//                note.setText(db.getNote(user_id,video_id));
                Log.wtf("ok!",v_name.getText().toString());
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }



}
