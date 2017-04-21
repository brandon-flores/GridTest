package bai.gridtest.Tabs;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bai.gridtest.Activities.VideoList;
import bai.gridtest.CategoryAdapter;
import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.Category;
import bai.gridtest.Models.User;
import bai.gridtest.R;

/**
 * Created by next on 4/12/2017.
 */

public class Watchlist extends Fragment{

    User user;
    DatabaseHelper db;
    List<String> c_title;
    CategoryAdapter adapter;
    int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String uname = getArguments().getString("uname");

        db = DatabaseHelper.getInstance(getContext());
        user = db.getUser(uname);

        View view = inflater.inflate(R.layout.tab_watchlist,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.grid);

        c_title = new ArrayList<String>();
        c_title.add("ADD CATEGORY");

        List<Category> categories = db.getAllUserCategory(user.getId());
        Log.wtf("CATEGORY SIZE",categories.size()+"");
        for (Category c : categories){
            c_title.add(c.getName());
        }

        adapter = new CategoryAdapter(view.getContext(),
                c_title);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position == 0){
                    showDialog();
                }else{

                    String cn = c_title.get(position);//category name
                    Log.wtf("??",cn );
                    Intent intent = new Intent(getActivity(),VideoList.class);
                    intent.putExtra("user",user.getUsername());
                    intent.putExtra("category",cn);
                    startActivity(intent);
                }
            }
        });

        gridView.setAdapter(adapter);

        return view;
    }



    private void showDialog(){
        //final Button image = new Button(getActivity());
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.prompt);
        dialog.setCancelable(true);


        Button button = (Button) dialog.findViewById(R.id.promptOk);
        Button button2 = (Button) dialog.findViewById(R.id.promptCancel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) dialog.findViewById(R.id.categoryTitleInput);
                String categoryTitle = editText.getText().toString();

                if(db.checkCategory(categoryTitle)){
                    Category category = db.getCategory(categoryTitle);
                    if(db.userHasCategory(category.getId(), user.getId())){
                        Toast.makeText(getActivity(),
                                "Category already exists!" + categoryTitle, Toast.LENGTH_SHORT).show();
                    }else{
                        db.addUserCategory(user.getId(),category.getId());
                        c_title.add(category.getName());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }else{
                    Category category = new Category(db.getAllCategories().size(), categoryTitle);
                    db.addCategory(category);
                    db.addUserCategory(user.getId(),category.getId());

                    c_title.add(category.getName());
                    adapter.notifyDataSetChanged();

                    dialog.dismiss();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}