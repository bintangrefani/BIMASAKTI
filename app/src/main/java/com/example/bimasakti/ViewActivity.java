package com.example.bimasakti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bimasakti.Database.DataHelper;

public class ViewActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView textViewDate, textViewLabel, textViewNbVisits, textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbHelper = new DataHelper(this);
        textViewDate = (TextView) findViewById(R.id.tvDate);
        textViewLabel = (TextView) findViewById(R.id.tvLabel);
        textViewNbVisits = (TextView) findViewById(R.id.tvNbVisits);
        textViewStatus = (TextView) findViewById(R.id.tvStatus);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM bimasakti WHERE LABEL = '" +
                getIntent().getStringExtra("LABEL") + "'", null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            textViewDate.setText(cursor.getString(0).toString());
            textViewLabel.setText(cursor.getString(1).toString());
            textViewNbVisits.setText(cursor.getString(2).toString());
            textViewStatus.setText(cursor.getString(3).toString());
        }

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
