package bai.gridtest.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bai.gridtest.Activities.VideoList;
import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.User;
import bai.gridtest.R;

/**
 * Created by next on 4/12/2017.
 */

public class Home extends Fragment {

    private static TextView username;
    private static ArrayAdapter<String> mAdapter;
    private static ArrayList<String> smh;
    private static ListView list;
    private static TextView name;
    private static ImageView pp;
    View view;
    User user;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_home,
                container, false);
        smh = new ArrayList<String>();
        smh.add("s");
        smh.add("m");
        smh.add("h");
        initViews();
        String uname = getArguments().getString("uname");
        String nname = getArguments().getString("name");
        User user = DatabaseHelper.getInstance(getContext()).getUser(uname);
        username.setText(uname);
        int id = checkImageID(user.getImage_id());
        pp.setImageResource(id);
        name.setText(nname);
        return view;
    }

    void initViews(){
        username = (TextView) view.findViewById(R.id.c_uname);
        name = (TextView) view.findViewById(R.id.c_name);
        pp = (ImageView) view.findViewById(R.id.user_profile_photo);
        list = (ListView) view.findViewById(R.id.recent);

        mAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,smh);
        mAdapter.notifyDataSetChanged();
        list.setAdapter(mAdapter);

    }

    int checkImageID(int pos){
        if(pos == 0){
            return R.drawable.avatar_1;
        }else{
            return R.drawable.avatar_2;
        }
    }
}
