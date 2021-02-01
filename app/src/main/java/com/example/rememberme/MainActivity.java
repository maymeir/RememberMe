package com.example.rememberme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText userNameEt;
    EditText passwordEt;
    TextView errorUser;
    TextView errorPass;
    private List<User> users;

    public MainActivity(List<User> users) {
        this.users = users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox rememberMeCb=findViewById(R.id.remember_cb);
        userNameEt=findViewById(R.id.username);
        passwordEt=findViewById(R.id.password);
        errorUser=findViewById(R.id.error_login_username_tv);
        errorPass=findViewById(R.id.error_login_password_tv);
        Button loginBtn=findViewById(R.id.login_btn);
        UserManager manager=UserManager.getInstance(MainActivity.this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userNameEt.getText().toString().isEmpty()){
                    errorUser.setText("please fill user name");
                    Toast.makeText(MainActivity.this, "please fill user name", Toast.LENGTH_SHORT).show();
                }
                if (passwordEt.getText().toString().isEmpty()){

                    errorPass.setText("please fill password");
                    Toast.makeText(MainActivity.this, "please fill password", Toast.LENGTH_SHORT).show();
                }
                if (users==null){
                     errorPass.setText("user null");
                }
                if (users != null) {
                    for (User user : users) {

                        if (user.getUsername().equals(userNameEt.getText().toString())) {

                            if (user.getPassword().equals(passwordEt.getText().toString())) {
                                Intent intent = new Intent(MainActivity.this, Main1Activity.class);
                                startActivity(intent);

                            }

                        } else
                            errorPass.setText("one of not correct");
                    }
                }

            }
        });

        Button registerBtn=findViewById(R.id.login_register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });




    }
}
