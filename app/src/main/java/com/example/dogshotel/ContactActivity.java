package com.example.dogshotel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.Locale;

public class ContactActivity extends Activity {
    final int CALL_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        ImageButton dial_btn = findViewById(R.id.dial_btn);

        dial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0502497343";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        ImageButton call_btn = findViewById(R.id.call_btn);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int hasCallPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if (hasCallPermission == PackageManager.PERMISSION_GRANTED) {
                        callPhone();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
                    }

                } else
                    callPhone();


            }
        });

        final ImageButton email_btn = findViewById(R.id.email_btn);
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String[] address = {"karinkrupetsky@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:karinkrupetsky@gmail.com"));
                intent.putExtra(Intent.EXTRA_TEXT, "You can contact us using email here , from us - Dog's Boutique");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email to Dog's Boutique");
                intent.putExtra((Intent.EXTRA_EMAIL), "karinkrupetsky@gmail.com");
                //intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "send email with:"));

            }
        });


        final ImageButton smsBtn = findViewById(R.id.sms_btn);

        smsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number = "0502497343";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
                intent.putExtra("sms_body", getResources().getString(R.string.sms_txt));
                startActivity(intent);
            }
        });
        ////////////////////////////////////////////////////////

        final ImageButton whatsapp_btn = findViewById(R.id.whatsapp_btn);
        whatsapp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0502497343";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.whatsapp_txt));
                sendIntent.putExtra("jid", number + "@s.whatsapp.net");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);

            }
        });







        final ImageButton add_contact = findViewById(R.id.add_contacts);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, "Karin" + " " + "Dogs Hotel");
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "0502497343");
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "karinkrupetsky@gmail.com");
                intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }


        });
    }




    private void callPhone() {
        String number = "0502497343";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        try {
            startActivity(intent);
        }
        catch(SecurityException e){

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                callPhone();
            else {
                Toast.makeText(this, "sorry, can't work without call permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
