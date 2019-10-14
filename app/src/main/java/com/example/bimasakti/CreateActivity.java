package com.example.bimasakti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bimasakti.Database.DataHelper;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button bSubmit;
    EditText editTextDate, editTextLabel, editTextNbVisits, edtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbHelper = new DataHelper(this);
        editTextDate = (EditText) findViewById(R.id.edtDate);
        editTextLabel = (EditText) findViewById(R.id.edtLabel);
        editTextNbVisits = (EditText) findViewById(R.id.edtNbVisits);
        edtStatus = (EditText) findViewById(R.id.edtStatus);
        bSubmit = (Button) findViewById(R.id.btnDaftar);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //menggunakan obyek SQLitedatabse, yang di dapatkan dari pemanggilan getWritetabledatabse melalui obyek dbHelper
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO bimasakti(ID, LABEL, NBVISITS, STATUS) VALUES('" +
                        editTextDate.getText().toString() + "','" +
                        editTextLabel.getText().toString() + "','" +
                        editTextNbVisits.getText().toString() + "','" +
                        edtStatus.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Create Successfull", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
