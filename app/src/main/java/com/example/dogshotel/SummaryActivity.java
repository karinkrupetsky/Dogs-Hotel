package com.example.dogshotel;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SummaryActivity extends Activity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_activity);
        TextView name = findViewById(R.id.name_dog);
        name.setText(getResources().getString(R.string.dogs_name)+" " + getIntent().getStringExtra( "name" ) );
        TextView age = findViewById(R.id.age_dog);
        age.setText(getResources().getString(R.string.dogs_age) + " "  + getIntent().getStringExtra( "age" ) );
        TextView phone = findViewById(R.id.phone_owner);
        phone.setText(getResources().getString(R.string.owner_phone) + " "  + getIntent().getStringExtra( "phone" ) );
        TextView email = findViewById(R.id.email_owner);
        email.setText(getResources().getString(R.string.owner_email)+  " "  + getIntent().getStringExtra( "email" ) );


        ImageButton timer = findViewById(R.id.timer);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Timer Dog's Boutique");
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 86400);
                intent.putExtra(AlarmClock.EXTRA_SKIP_UI,false);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
            }
        });




    }
}
