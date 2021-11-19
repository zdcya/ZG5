package com.bawei.my_project_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ProgressView.ProgressVeiwListener {

    private ProgressView proview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        proview.init(this);


    }


    @Override
    public void finish() {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));


        overridePendingTransition(android.R.anim.slide_in_left,0);

        super.finish();

    }

    private void initView() {
        proview = findViewById(R.id.proview);

    }
}