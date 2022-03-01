package com.rikkei.training.fragment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView imgReplay,imgPlay,imgPrevious,imgNext;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getFragment(Fragment1.newInstance());
        Fragment1.newInstance();
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(Fragment2.newInstance());
                Fragment2.newInstance();
            }
        });
        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(Fragment1.newInstance());
                Fragment1.newInstance();
            }
        });
    }
    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
    }
    public void init(){
        tvTitle=findViewById(R.id.tvNameMusic);
        imgNext=findViewById(R.id.imgButNext);
        imgPlay=findViewById(R.id.imgButPlay);
        imgPrevious=findViewById(R.id.imgButPrevious);
        imgReplay=findViewById(R.id.imgButReplay);
    }
}