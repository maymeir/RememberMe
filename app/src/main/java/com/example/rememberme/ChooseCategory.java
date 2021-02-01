package com.example.rememberme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseCategory extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_menu)
        {
            Intent intent= new Intent(ChooseCategory.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            if (item.getItemId() == R.id.action_table) {
                Intent intent1 = new Intent(ChooseCategory.this, RecordsTable.class);
                startActivity(intent1);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_layout);

        TextView name=findViewById(R.id.name);
        SharedPreferences sp=getSharedPreferences("INFO",MODE_PRIVATE);
        name.setText("  "+sp.getString("name",""));



        Button car_btn=findViewById(R.id.cars_btn);
        car_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ChooseCategory.this,GameActivity.class);
                intent.putExtra("level",getIntent().getIntExtra("level",0));
                intent.putExtra("category",1);
                startActivity(intent);
                finish();
            }
        });
        Button animals_btn=findViewById(R.id.animals_btn);
        animals_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ChooseCategory.this,GameActivity.class);
                intent.putExtra("level",getIntent().getIntExtra("level",0));
                intent.putExtra("category",2);
                startActivity(intent);
                finish();
            }
        });
        Button flags_btn=findViewById(R.id.cartoons_btn);
        flags_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ChooseCategory.this,GameActivity.class);
                intent.putExtra("level",getIntent().getIntExtra("level",0));
                intent.putExtra("category",3);
                startActivity(intent);
                finish();
            }
        });






    }
}
