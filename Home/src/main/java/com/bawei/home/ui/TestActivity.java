package com.bawei.home.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei.home.FragmentAdapter;
import com.bawei.home.R;
import com.bawei.home.ui.HomeFragment;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private ViewPager testVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        testVp.setAdapter(fragmentAdapter);
    }

    private void initView() {
        testVp = findViewById(R.id.test_vp);
    }
}