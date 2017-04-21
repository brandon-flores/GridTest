package bai.gridtest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bai.gridtest.Helper.DatabaseHelper;
import bai.gridtest.Models.User;
import bai.gridtest.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //private static DatabaseHelper db;
    private static EditText username;
    private static EditText password;
    private static Button btnlogin;
    private static TextView register;
    private static User user;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initObjects();
        initViews();
    }

    void initViews(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button)   findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(this);
        register = (TextView) findViewById(R.id.registerLink);
        register.setOnClickListener(this);

    }
    void initObjects(){
        db = DatabaseHelper.getInstance(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btnLogin:
                String uname = username.getText().toString();
                String pass  = password.getText().toString();
                if(db.checkUser(uname,pass)){
                    user = db.getUser(uname);
                    Log.wtf("LOGIN REQUEST", user.getName());
                    intent = new Intent(this, MainActivity.class);
                    //Log.wtf("Name inside logAct",user.getId()+"");
                    intent.putExtra("Username",user.getUsername());
                    startActivity(intent);
                }else{
                    Log.wtf("LOGIN REQUEST", "INVALID!");
                    Toast.makeText(this,"INVALID LOGIN REQUEST!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.registerLink:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;


        }

    }
}
