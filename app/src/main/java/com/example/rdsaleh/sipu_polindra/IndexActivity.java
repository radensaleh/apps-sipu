package com.example.rdsaleh.sipu_polindra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    private Button btnLanjut;
    private ImageView imgSipu;
    private TextView txtindex, txtdesc, txthastag;
    private Animation uptodown, downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnLanjut = (Button) findViewById(R.id.btnLanjut);

        imgSipu = (ImageView) findViewById(R.id.img);

        txtindex  = (TextView) findViewById(R.id.txtindex);
        txtdesc   = (TextView) findViewById(R.id.txtdesc);
        txthastag = (TextView) findViewById(R.id.txthastag);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        imgSipu.setAnimation(uptodown);
        txtindex.setAnimation(uptodown);
        txtdesc.setAnimation(downtoup);
        txthastag.setAnimation(downtoup);
        btnLanjut.setAnimation(downtoup);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, IntroSlide.class);
                startActivity(i);
            }
        });

    }
}
