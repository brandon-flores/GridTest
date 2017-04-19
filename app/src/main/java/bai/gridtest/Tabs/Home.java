package bai.gridtest.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.User;
import bai.gridtest.R;

/**
 * Created by next on 4/12/2017.
 */

public class Home extends Fragment {

    private static TextView username;
    private static TextView name;
    private static ImageView pp;
    View view;
    User user;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_home,
                container, false);
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
    }

    int checkImageID(int pos){
        if(pos == 0){
            return R.drawable.avatar_1;
        }else{
            return R.drawable.avatar_2;
        }
    }
}
