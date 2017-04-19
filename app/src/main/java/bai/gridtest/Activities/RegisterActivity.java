package bai.gridtest.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bai.gridtest.AvatarAdapter;
import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.User;
import bai.gridtest.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper db;
    private static EditText name;
    private static EditText username;
    private static EditText password;
    private static EditText cpassword;
    private static Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initObjects();
        initViews();
    }

    void initViews(){
        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpassword);
        btnregister = (Button)   findViewById(R.id.btnRegister);
        btnregister.setOnClickListener(this);
    }
    void initObjects(){
        db = DatabaseHelper.getInstance(this);
    }


    @Override
    public void onClick(View v) {
        String uname = username.getText().toString();
        String n = name.getText().toString();
        String pass  = password.getText().toString();
        String cpass  = cpassword.getText().toString();
        if(uname!=null && n!=null && pass!=null && cpass!=null){
            if(db.checkUser(uname)){
                Log.wtf("REGISTER REQUEST", "INVALID! ALREADY EXISTS");
                Toast.makeText(this, "USERNAME ALREADY EXISTS!", Toast.LENGTH_SHORT).show();
            }else{
                if(pass.equals(cpass)){
                    User user = new User(db.getAllUser().size(),uname,n,pass);
                    Log.wtf("USER ID",user.getId()+"");
                    Toast.makeText(this, "REGISTERED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                    showAlertDialog(user);
                    db.addUser(user);
                }else{
                    Log.wtf("REGISTER REQUEST","INVALID! PASSWORD DON'T MATCH");
                    Toast.makeText(this, "PASSWORDS DON'T MATCH", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this,"PLEASE FILL UP THE FORM PROPERLY,",Toast.LENGTH_SHORT).show();
        }

    }


    private void showAlertDialog(final User user) {
        final GridView gridView = new GridView(this);

        List<Integer>  mList = new ArrayList<Integer>();
        for (int i = 1; i < 36; i++) {
            mList.add(i);
        }

        gridView.setAdapter(new AvatarAdapter(this));
        gridView.setNumColumns(5);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(gridView);
        builder.setTitle("Choose Avatar");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            user.setImage_id(position);

            }
        });


        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.show();
    }




}
