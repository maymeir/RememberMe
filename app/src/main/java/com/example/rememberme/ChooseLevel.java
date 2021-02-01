package com.example.rememberme;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ChooseLevel  extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_menu)
        {
            Intent intent= new Intent(ChooseLevel.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            if (item.getItemId() == R.id.action_table) {
                Intent intent1 = new Intent(ChooseLevel.this, RecordsTable.class);
                startActivity(intent1);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
    TextView nameTv;
    SharedPreferences sp;
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_level);

         nameTv= findViewById(R.id.name);
         sp=getSharedPreferences("INFO",MODE_PRIVATE);
        nameTv.setText("  "+sp.getString("name",""));

        Button easy_btn= findViewById(R.id.easyBtn);
        easy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChooseLevel.this, ChooseCategory.class);

                intent.putExtra("level", 1);
                startActivity(intent);
                finish();

            }
        });


        Button medium_btn= findViewById(R.id.mediumBtn);
        medium_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChooseLevel.this, ChooseCategory.class);
                intent.putExtra("level", 2);
                startActivity(intent);
                finish();

            }
        });


        Button hard_btn= findViewById(R.id.hardBtn);
        hard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChooseLevel.this, ChooseCategory.class);
                intent.putExtra("level", 3);
                startActivity(intent);
                finish();

            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor =sp.edit();
        editor.putString("name",sp.getString("name",""));
        editor.putBoolean("first_run",false);
        editor.commit();
    }

}
