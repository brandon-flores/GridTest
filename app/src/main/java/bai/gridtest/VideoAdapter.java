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

import java.util.List;

import bai.gridtest.Activities.MainActivity;
import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.Category;

/**
 * Created by next on 4/19/2017.
 */

public class VideoAdapter extends BaseAdapter implements ListAdapter{
    private Context mContext;
    private final List<String> c_title;
    private final int Imageid;
    DatabaseHelper db = DatabaseHelper.getInstance(mContext);

    public VideoAdapter(Context c,List<String> c_title ) {
        mContext = c;
        this.Imageid = R.drawable.add_category;
        this.c_title = c_title;
    }

    @Override
    public int getCount() {
        return c_title.size();
    }

    @Override
    public Object getItem(int position) {
        return c_title.get(position);
}

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            list = new View(mContext);
            list  = inflater.inflate(R.layout.video_item, null);

            TextView textView = (TextView) list.findViewById(R.id.vid_title);
            textView.setText(c_title.get(position));

            ImageButton edit = (ImageButton) list.findViewById(R.id.edit);
            ImageButton delete = (ImageButton) list.findViewById(R.id.delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"EDIT~",Toast.LENGTH_SHORT);
                    Log.wtf("editing","edited");
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper.getInstance(mContext);
                }
            });

        } else {
            list = (View) convertView;
        }



        return list;
    }



}
