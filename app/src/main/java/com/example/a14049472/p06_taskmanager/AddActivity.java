package com.example.a14049472.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    int reqCode = 12345;

    EditText etName;
    EditText etDesc;
    Button btnAdd;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName=(EditText)findViewById(R.id.etName);
        etDesc=(EditText)findViewById(R.id.etDesc);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnCancel=(Button)findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,5);

                Intent intent = new Intent (AddActivity.this,MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this,reqCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);

                String data  = etName.getText().toString();
                String data2 = etDesc.getText().toString();
                DBHelper dbh = new DBHelper(AddActivity.this);
                long row_affected = dbh.insertTask(data,data2);
                dbh.close();
                if(row_affected!=-1){
                    Toast.makeText(AddActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent (AddActivity.this,MainActivity.class);
                    startActivityForResult(i,RESULT_OK);
                    finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AddActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
