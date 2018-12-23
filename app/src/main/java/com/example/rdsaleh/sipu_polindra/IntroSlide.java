package com.example.rdsaleh.sipu_polindra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.rdsaleh.sipu_polindra.introSlide.SlideFolafo;
import com.example.rdsaleh.sipu_polindra.introSlide.SlideKompa;
import com.example.rdsaleh.sipu_polindra.introSlide.SlideKopen;
import com.example.rdsaleh.sipu_polindra.introSlide.SlidePopi;
import com.example.rdsaleh.sipu_polindra.introSlide.SlideRpi;

import agency.tango.materialintroscreen.MaterialIntroActivity;

public class IntroSlide extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new SlideKompa());
        addSlide(new SlideKopen());
        addSlide(new SlideRpi());
        addSlide(new SlidePopi());
        addSlide(new SlideFolafo());

    }
    @Override
    public void onFinish() {
        super.onFinish();
        Intent i = new Intent(IntroSlide.this, LoginActivity.class);
        startActivity(i);
    }

}
