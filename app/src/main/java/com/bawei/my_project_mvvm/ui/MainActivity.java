package com.bawei.my_project_mvvm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bawei.my_project_mvvm.R;
import com.bawei.resource.customView.ProgressView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProgressView.ProgressVeiwListener {

    private ProgressView proview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        proview.init(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
        

    }


    @Override
    public void finish() {
        startActivity(new Intent(MainActivity.this, navigationActivity.class));


        overridePendingTransition(R.anim.anim_in,0);

        super.finish();

    }

    private void initView() {
        proview = findViewById(R.id.proview);

    }
}