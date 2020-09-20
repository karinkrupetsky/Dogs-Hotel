package com.example.dogshotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView nameTv= findViewById(R.id.name_output);


        nameTv.setText(getResources(). getString (R.string.hello) + " " + getIntent().getStringExtra( "name" ) + ", "  + getResources(). getString (R.string.choose_from));

        Button Back_btn = findViewById(R.id.back_btn);
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        Button registrationButtom = findViewById(R.id.registration_btn);
        registrationButtom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, RegistrationActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(intent);
            }
        });
        Button contactButtom = findViewById(R.id.contact_btn);
        contactButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

    }
}
