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

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button bSubmit;
    EditText editTextDate, editTextLabel, editTextNbVisits, editTextStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbHelper = new DataHelper(this);

        dbHelper = new  DataHelper(this);
        editTextDate = (EditText) findViewById(R.id.edtDate);
        editTextLabel = (EditText) findViewById(R.id.edtLabel);
        editTextNbVisits = (EditText) findViewById(R.id.edtNbVisits);
        editTextStatus = (EditText) findViewById(R.id.edtStatus);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM bimasakti WHERE LABEL = '" +
                getIntent().getStringExtra("LABEL") + "'", null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            editTextDate.setText(cursor.getString(0).toString());
            editTextLabel.setText(cursor.getString(1).toString());
            editTextNbVisits.setText(cursor.getString(2).toString());
            editTextStatus.setText(cursor.getString(3).toString());
        }

        bSubmit = (Button) findViewById(R.id.btnDaftar);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE bimasakti SET LABEL = '" +
                        editTextLabel.getText().toString() + "', NBVISITS = '" +
                        editTextNbVisits.getText().toString() + "', STATUS = '" +
                        editTextStatus.getText().toString() + "' WHERE ID = '" +
                        editTextDate.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
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
