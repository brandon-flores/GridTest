package bai.gridtest.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bai.gridtest.R;

/**
 * Created by next on 4/12/2017.
 */

public class Settings extends Fragment implements View.OnClickListener {
    // private DatabaseHelper databaseHelper;
    private TextView editProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_options,
                container, false);

        // initObjects();


        editProfile = (TextView) view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(this);
//        User a = new User(0,"iris","asd","asda");
//        databaseHelper.addUser(a);
//        databaseHelper.addCategory(new Category(a.getId(),"Category a"));
//        Category b = databaseHelper.getCategory(a.getId());
//        Log.wtf("CatFetch",b.getName());
        return view;
    }


    // void initObjects(){
    // databaseHelper = new DatabaseHelper(getActivity());
    // }

    @Override
    public void onClick(View v) {
        // databaseHelper.addUser(new User(0,textView.getText().toString(),"ayi","haha"));
        //Log.wtf("check",databaseHelper.checkUser("iris")+"");
        //textView2.setText(databaseHelper.getUser("iris").getUsername());
        //final FragmentTransaction ft = getFragmentManager().beginTransaction();
        Toast.makeText(getActivity(),
                "edit is clicked!", Toast.LENGTH_SHORT).show();
        // ft.replace(R.id., new EditProfile(), "NewFragmentTag");
        //ft.commit();
        // ft.addToBackStack(null);
    }
}



