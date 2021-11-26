package com.bawei.classify.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.bawei.utils.FragmentAdapter;
import com.zy.home.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new ClassifyFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        vp.setAdapter(fragmentAdapter);


    }

    private void initView() {
        vp = findViewById(R.id.vp);
    }
}