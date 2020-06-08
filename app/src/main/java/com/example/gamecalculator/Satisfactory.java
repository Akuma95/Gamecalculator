package com.example.gamecalculator;

import android.os.Bundle;

import com.example.gamecalculator.ui.main.SectionsPagerAdapterSatisfactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gamecalculator.ui.main.SectionsPagerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Satisfactory extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText category;
    private EditText title;
    private CheckBox isOre;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satisfactory);
        SectionsPagerAdapterSatisfactory sectionsPagerAdapter = new SectionsPagerAdapterSatisfactory(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        submit = (Button)SatisfactoryItem.findViewById(R.id.successForm);
        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = findViewById(R.id.categorie);
                title = findViewById(R.id.itemName);
                isOre = findViewById(R.id.isOre);
                writeItem();
            }
        });
    }

    private void writeItem() {
        String categoryS = category.getText().toString();
        String titleS = title.getText().toString();
        String isOreS;
        if (isOre.isChecked()) {
            isOreS = "true";
        } else {
            isOreS = "false";
        }

        mDatabase.child("satisfactory").child(categoryS).setValue(titleS);
        mDatabase.child("satisfactory").child(categoryS).setValue(isOreS);
    }
}