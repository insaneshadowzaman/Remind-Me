package com.example.akibtalcompany.remindme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import static com.example.akibtalcompany.remindme.AppHelper.NOTIFICATION_ID;
import static com.example.akibtalcompany.remindme.NotiBuilder.NOTINAME;

public class AddTaskActivity extends Activity {

    Spinner hh, mm, ampm;
    Button add;
    EditText name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initViews();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notName = name.getText().toString();
                String amp = ampm.getSelectedItem().toString();
                Integer h =  Integer.parseInt(hh.getSelectedItem().toString());
                Integer m =  Integer.parseInt(mm.getSelectedItem().toString());

                if(Objects.equals(amp, "pm")) h += 12;
                AppHelper.addNoteToDB(AddTaskActivity.this, notName, h, m);
                Toast.makeText(AddTaskActivity.this, "ADDED", Toast.LENGTH_LONG).show();
                name.setText("");
                ampm.setSelection(0);
                hh.setSelection(0);
                mm.setSelection(0);
//                AppHelper.dumbDB(AddTaskActivity.this);

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, h);
                cal.set(Calendar.MINUTE, m);

                Intent intent = new Intent(AddTaskActivity.this, NotiBuilder.class);
                intent.putExtra(NotiBuilder.NOTINAME, notName);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddTaskActivity.this,
                        NOTIFICATION_ID,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                if (am != null) {
                    am.set(
                            AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(),
                            pendingIntent);
                }
            }

        });

    }

    private void initViews() {
        hh = findViewById(R.id.spinner_HH);
        mm = findViewById(R.id.spinner_MM);
        ampm = findViewById(R.id.spinner_ampm);
        add = findViewById(R.id.add_btn);
        name = findViewById(R.id.add_task_task_name);
    }
}
