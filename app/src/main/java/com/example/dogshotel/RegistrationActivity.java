package com.example.dogshotel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class RegistrationActivity extends Activity {
    LocalDateTime now = LocalDateTime.now();
    ImageView resultIv;
    final int CAMERA_REQUEST = 1;
    static final int REQUEST_VIDEO_CAPTURE = 2;
    int currentMinute;
    int currentHour;
    Calendar calendar;
    String amPm;
    TimePickerDialog timePickerDialog;
    EditText choose;
    TextView choosen_time_Tv ;
    int hour1,minute1;
    Bitmap bitmap = null;
    VideoView videoIm = null;
    boolean recordedVideo = false;
    byte[] byteArray;


    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        choosen_time_Tv = findViewById(R.id.ChoosenTime);
        final ScrollView scrollView = findViewById(R.id.scroll);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });




        resultIv = findViewById(R.id.dog_pic);
        videoIm = findViewById(R.id.dog_video);


        final EditText dog_name = findViewById(R.id.dog_name);
        final EditText dog_age = findViewById(R.id.dog_age);
        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        final ImageView dogi_pic = findViewById(R.id.dog_pic);






        TextView size= findViewById(R.id.size);
        //nameTv.setText(getIntent().getStringExtra( "name" ));

        size.setText( getIntent().getStringExtra( "name" ) +" ," + getResources(). getString (R.string.register_your_dog));


        final Button sikum_order = findViewById(R.id.sikum_order);
        sikum_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dogname = dog_name.getText().toString();
                String dogage = dog_age.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();

                Intent intent = new Intent(RegistrationActivity.this, SummaryActivity.class);
                intent.putExtra( "name", dogname);
                intent.putExtra( "age", dogage);
                intent.putExtra( "phone", Phone);
                intent.putExtra("email" , Email);
                startActivity(intent);



            }

    });





    ImageButton video_btn = findViewById(R.id.video_btn);
        video_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent video_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (video_intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(video_intent, REQUEST_VIDEO_CAPTURE);
            }

        }
    });

    ImageButton camera_btn = findViewById(R.id.camera_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
            //enable_registration_btn();
        }
    });


    final ImageButton clock_icon = findViewById(R.id.clock_icon);
    //final TextView choosen_time_Tv = findViewById(R.id.ChoosenTime);
        clock_icon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(RegistrationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(minute>9)
                    {
                        if(hourOfDay>9)
                        {
                            String time = hourOfDay + ":" + minute;
                            choosen_time_Tv.setText(time);
                        }
                        else
                        {
                            String time ="0" + hourOfDay + ":" + minute;
                            choosen_time_Tv.setText(time);
                        }
                    }
                    else
                    {
                        if(hourOfDay >9) {
                            String time = hourOfDay + ":0" + minute;
                            choosen_time_Tv.setText(time);
                        }
                        else
                        {
                            String time ="0" + hourOfDay + ":0" + minute;
                            choosen_time_Tv.setText(time);
                        }
                    }

                    // choosen_time_Tv.setText(hourOfDay + ":" + minute);
                }
            }, currentHour, currentMinute, true);
            timePickerDialog.setTitle("Select time:");
            timePickerDialog.show();
            //enable_registration_btn();
        }
    });



    final ImageButton calender_icon = findViewById(R.id.calender_icon);
    final TextView choosenDay = findViewById(R.id.ChoosenDay);
    //final DatePickerDialog datePickerDialog;
        calender_icon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar calendar = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    choosenDay.setText((new SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault())).format(calendar.getTime()));
                }
            };

            DatePickerDialog dp = new DatePickerDialog(RegistrationActivity.this,datePickerDialog,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dp.getDatePicker().setMinDate(System.currentTimeMillis()+24*60*60*1000);
            dp.show();
            //enable_registration_btn();
        }
    });


    /*Handles end of order, missing fields and final message*/
    //videoIm = (VideoView)findViewById(R.id.dog_video);
    final ProgressBar progressBar = findViewById(R.id.progress_bar);
    final TextView endOrderTv = findViewById(R.id.end_order_tv);
    Button orderBtn = findViewById(R.id.order_btn);
    linearLayout = findViewById(R.id.linear);
        orderBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //check if all the edit text fields are filled
            for(int i=0;i<linearLayout.getChildCount();i++) {
                View view = linearLayout.getChildAt(i);
                if (view instanceof EditText) {
                    if (((EditText) view).getText().toString().equals("")) {
                        Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.missing_input), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }

            if(choosenDay.getText().toString().equals("")){
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.missing_input), Toast.LENGTH_SHORT).show();
                return;
            }
            TextView ChoosenTime = findViewById(R.id.ChoosenTime);
            if(ChoosenTime.getText().toString().equals("")){
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.missing_input), Toast.LENGTH_SHORT).show();
                return;
            }
            else if(ChoosenTime.getText().toString()!= "") {
                String s = ChoosenTime.getText().toString();
                Scanner scan = new Scanner(s).useDelimiter(":");
                int hour = scan.nextInt();
                int minute = scan.nextInt();
                if ((hour < 10) || (hour > 20)) {
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.sim_lev), Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (minute!= 00) {
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.sim_lev), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if(bitmap==null) {
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.missing_input), Toast.LENGTH_SHORT).show();
                return;
            }
            if(recordedVideo==false)
            {
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.missing_input), Toast.LENGTH_SHORT).show();
                return;
            }



            /*Handles progress bar*/
            progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    endOrderTv.setText(getResources().getString(R.string.end_order_txt));
                }

            }, 1000);
        }
    });



    final Button add_event = findViewById(R.id.add_event);
        add_event.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (choosen_time_Tv.getText().toString() != ""
                    && choosenDay.getText().toString() != "" ) {

                String s = choosen_time_Tv.getText().toString();
                Scanner scan = new Scanner(s).useDelimiter(":");
                int hour = scan.nextInt();
                hour1=hour;
                int minute = scan.nextInt();
                minute1=minute;
                if((hour>=10) &&  (hour<=20&& minute==0)) {

                    String ss = choosenDay.getText().toString();
                    Scanner scan1 = new Scanner(ss).useDelimiter("/");
                    int Day = scan1.nextInt();
                    int month = scan1.nextInt();
                    int year = scan1.nextInt();

                    Calendar beginCal = Calendar.getInstance();
                    beginCal.clear();
                    beginCal.set(year, month - 1, Day, hour , minute);
                    Calendar endCal = Calendar.getInstance();
                    endCal.clear();
                    endCal.set(year, month - 1, Day, hour + 2, minute);
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Registration")
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, "Holon")
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginCal.getTimeInMillis())
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }

                else{
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.sim_lev), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if(choosen_time_Tv.getText().toString() != "")
                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.dont_pick_hour), Toast.LENGTH_SHORT).show();
                if(choosenDay.getText().toString() != "")
                    Toast.makeText(RegistrationActivity.this,  getResources().getString(R.string.didnt_pick_day), Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.day_and_hour), Toast.LENGTH_SHORT).show();

            }
            //enable_registration_btn();
        }
    });
}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");

            resultIv.setImageBitmap(bitmap);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoIm.setVideoURI(videoUri);
            videoIm.setMediaController(new MediaController(RegistrationActivity.this));
            //videoIm.setZOrderOnTop(true);
            recordedVideo = true;
            videoIm.start();
        }
    }








/*
    private void enable_registration_btn() {
        Button order = findViewById(R.id.order_btn);

        EditText dog_name = findViewById(R.id.dog_name);
        EditText dog_age = findViewById(R.id.dog_age);
        EditText name_owner = findViewById(R.id.name_owner);
        EditText phone = findViewById(R.id.phone);
        EditText email = findViewById(R.id.email);
        TextView ChoosenDay = findViewById(R.id.ChoosenDay);
        TextView ChoosenTime = findViewById(R.id.ChoosenTime);
        ImageView dog_pic = findViewById(R.id.dog_pic);
        VideoView dog_video = findViewById(R.id.dog_video);

        if(dog_pic.getDrawable() != null && dog_video.getDrawableState()!= null
        &&  dog_name.getText().length() > 0 && dog_age.getText().length() > 0
        &&name_owner.getText().length() > 0 && phone.getText().length() > 0
        &&email.getText().length() > 0 && ChoosenDay.getText().length() > 0
        && ChoosenTime.getText().length() > 0 ) {
            order.setEnabled(true);
        }

        if(order.isEnabled())
        {
            if((hour1<=10) ||  (hour1>=20 && minute1!=0)) {
                Toast.makeText(this, "אנו עובדים 10:00-20:00 כל שעה עגולה בלבד", Toast.LENGTH_SHORT).show();

            }
}
        }

 */


    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setTitle("חבל...");
        builder.setMessage("אתה בטוח שאתה רוצה לוותר על פינוק לכלב שלך?");
        builder.setCancelable(true);
        builder.setNegativeButton("כן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("לא", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}

