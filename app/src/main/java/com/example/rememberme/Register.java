package com.example.rememberme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Register extends AppCompatActivity {
    EditText usernameEt;
    EditText fullNameEt;
    EditText passwordEt;
    EditText passwordAgainEt;
    EditText emailEt;
    TextView errorName, errorUserName, errorPassword, errorEmail, errorPasswordAgain, errorNotMatch;
    String fullName=null;
    String username=null;
    String email=null;
    String password=null;
    ArrayList<User> users;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        fullNameEt = findViewById(R.id.full_name_et);
        usernameEt = findViewById(R.id.register_username);
        emailEt = findViewById(R.id.register_email);
        passwordEt = findViewById(R.id.register_password);
        passwordAgainEt = findViewById(R.id.register_password_again);
        errorName = findViewById(R.id.error_fullName_tv);
        errorUserName = findViewById(R.id.error_username_tv);
        errorEmail = findViewById(R.id.error_email_tv);
        errorPassword = findViewById(R.id.error_password_tv);
        errorNotMatch = findViewById(R.id.error_not_match_tv);
        builder = new AlertDialog.Builder(Register.this);
        builder.setTitle("register made").setMessage("you have register").setPositiveButton("press to log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullNameEt.getText().toString().isEmpty()) {
                    errorName.setText("please fill fullName");
                    Toast.makeText(Register.this, "You have to fill username ", Toast.LENGTH_SHORT).show();
                } else {
                    errorName.setText("");
                    fullName = fullNameEt.getText().toString();
                }
                if (usernameEt.getText().toString().isEmpty()) {
                    errorUserName.setText("please fill user name");
                    Toast.makeText(Register.this, "You have to fill username", Toast.LENGTH_LONG).show();

                } else {
                    errorUserName.setText("");
                    if (usernameEt.getText().toString().length() > 4) {
                        username = usernameEt.getText().toString();
                    } else {errorUserName.setText(" fullName have to be at least 4 characters");}
                }

                if (emailEt.getText().toString().isEmpty()) {
                    errorEmail.setText("please fill email");
                    Toast.makeText(Register.this, "You have to fill email", Toast.LENGTH_LONG).show();
                    errorEmail.setText("");
                } else {
                    email = emailEt.getText().toString();
                }

                if (passwordEt.getText().toString().isEmpty()) {
                    errorPassword.setText("please fill password");
                    Toast.makeText(Register.this, "You have to fill password", Toast.LENGTH_LONG).show();

                } else {
                    errorPassword.setText("");
                    if (passwordEt.getText().toString().length() < 4) {
                        errorPassword.setText("password have to be at least 4 characters");
                        Toast.makeText(Register.this, "password have to be at least 4 characters", Toast.LENGTH_LONG).show();
                    } else errorPassword.setText("");
                }

                if (passwordAgainEt.getText().toString().isEmpty()) {
                    errorNotMatch.setText("please fill password again");
                    Toast.makeText(Register.this, "please fill password again", Toast.LENGTH_LONG).show();

                } else {
                    errorNotMatch.setText("");
                    if (!passwordEt.getText().toString().equals(passwordAgainEt.getText().toString())) {
                        errorNotMatch.setText("the password are not match");
                    } else {
                        password = passwordEt.getText().toString();
                    }
                }

                if (users != null) {
                    for (User user : users) {

                        if (user.getUsername().equals(usernameEt.getText().toString())) {
                            errorUserName.setText("username already exist");
                            Toast.makeText(Register.this, "username already exist please choose other", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                if (users == null) {
                    users = new ArrayList<>();
                }
                 if((fullName!=null)&&(username!=null)&&(email!=null)&&(password!=null)) {
                     User user = new User(fullName, username, email, password);
                     UserManager manager=UserManager.getInstance(Register.this);
                     manager.addUser(user);


                     builder.show();

                 }else {
                     Toast.makeText(Register.this, "fill all", Toast.LENGTH_LONG).show();

                 }
            }
        });

        Button loginBtn = findViewById(R.id.register_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
