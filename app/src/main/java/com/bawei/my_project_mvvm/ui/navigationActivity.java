package com.bawei.my_project_mvvm.ui;

import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.mvvmcore.view.BaseActivity;
import com.bawei.my_project_mvvm.R;

public class navigationActivity extends BaseActivity {
    private androidx.viewpager.widget.ViewPager navigationVp;
    private android.widget.RadioGroup navigationRg;
    private android.widget.RadioButton radio1;
    private android.widget.RadioButton radio2;
    private android.widget.RadioButton radio3;
    private android.widget.RadioButton radio4;
    private android.widget.RadioButton radio5;

    @Override
    protected void initEnv() {

        setContentView(R.layout.navigation_activity);
        initView();



        navigationRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.radio1:

                        break;
                }
            }
        });
    }


    private void initView() {
        navigationVp = findViewById(R.id.navigation_vp);
        navigationRg = findViewById(R.id.navigation_rg);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
    }
}
