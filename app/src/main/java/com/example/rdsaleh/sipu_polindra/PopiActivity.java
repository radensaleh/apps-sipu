package com.example.rdsaleh.sipu_polindra;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PopiActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private SlideAdapterPopi myAdapter;

    private TextView[] tdots;

    private Button btnNext;
    private Button btnPrev;
    private Button btnBackToDashboard;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popi);

        viewPager = (ViewPager) findViewById(R.id.viewPagerPopi);
        myAdapter = new SlideAdapterPopi(this);
        viewPager.setAdapter(myAdapter);

        dotLayout = (LinearLayout) findViewById(R.id.dots);

        btnNext   = (Button) findViewById(R.id.btnNext);
        btnPrev   = (Button) findViewById(R.id.btnPrev);
        btnBackToDashboard = (Button) findViewById(R.id.btnBackToMenu);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

        //onclick
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage + 1);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage - 1);
            }
        });

        btnBackToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void addDotsIndicator(int position){

        tdots = new TextView[5];
        dotLayout.removeAllViews();

        for(int i = 0; i < tdots.length; i++){
            tdots[i] = new TextView(this);
            tdots[i].setText(Html.fromHtml("&#8226;"));
            tdots[i].setTextSize(45);
            tdots[i].setTextColor(getResources().getColor(R.color.colorTransparantWhite));

            dotLayout.addView(tdots[i]);
        }

        if( tdots.length > 0 ){
            tdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if( i == 0 ){
                btnNext.setEnabled(true);
                btnPrev.setEnabled(false);
                btnBackToDashboard.setEnabled(true);

                btnNext.setVisibility(View.VISIBLE);
                btnPrev.setVisibility(View.INVISIBLE);
                btnBackToDashboard.setVisibility(View.VISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("");

            }else if( i == tdots.length -1 ){
                btnNext.setEnabled(false);
                btnPrev.setEnabled(true);
                btnBackToDashboard.setEnabled(false);

                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnBackToDashboard.setVisibility(View.INVISIBLE);

                btnPrev.setText("Back");

            }else{
                btnNext.setEnabled(true);
                btnPrev.setEnabled(true);
                btnBackToDashboard.setEnabled(false);

                btnNext.setVisibility(View.VISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnBackToDashboard.setVisibility(View.INVISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
