package com.example.rdsaleh.sipu_polindra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsaleh.sipu_polindra.api.ResponseMhs;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private TextView txtnama, txtemail, txtprodi, txtnim, txtadds, txtnohp;
    private LinearLayout LLnim, LLprodi, LLalamat, LLnohp;
    private Animation uptodown, downtoup, lefttoright, righttoleft;
    private Button btnEditInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright  = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft  = AnimationUtils.loadAnimation(this,R.anim.righttoleft);

        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        txtnama    = (TextView) findViewById(R.id.txtnama);
        txtemail   = (TextView) findViewById(R.id.txtemail);
        txtnim     = (TextView) findViewById(R.id.txtnim);
        txtprodi   = (TextView) findViewById(R.id.txtprodi);
        txtadds    = (TextView) findViewById(R.id.txtadds);
        txtnohp    = (TextView) findViewById(R.id.txtnohp);

        LLnim      = (LinearLayout) findViewById(R.id.LLnim);
        LLprodi    = (LinearLayout) findViewById(R.id.LLprodi);
        LLalamat   = (LinearLayout) findViewById(R.id.LLalamat);
        LLnohp     = (LinearLayout) findViewById(R.id.LLnohp);
        btnEditInfo = (Button) findViewById(R.id.btnEditInfo);

        imgProfile.setAnimation(uptodown);
        txtnama.setAnimation(uptodown);
        txtemail.setAnimation(uptodown);
        LLnim.setAnimation(lefttoright);
        LLprodi.setAnimation(righttoleft);
        LLalamat.setAnimation(downtoup);
        LLnohp.setAnimation(downtoup);
        btnEditInfo.setAnimation(downtoup);

        dataMhs();

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(ProfileActivity.this, EditInfoActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });
    }

    public void dataMhs(){
        Intent i = getIntent();
        Bundle b = i.getExtras();

        String getNim = (String) b.get("nim");

        Call<ResponseMhs> call = RetrofitClient
                .getInstance()
                .baseAPI()
                .getMhs(getNim);

        call.enqueue(new Callback<ResponseMhs>() {
            @Override
            public void onResponse(Call<ResponseMhs> call, Response<ResponseMhs> response) {
                String nim  = response.body().getNim();
                String nama = response.body().getNama_mhs();
                String pro  = response.body().getId_prodi();
                String pass = response.body().getPassword();
                String email = response.body().getEmail();
                String addrs = response.body().getAlamat();
                String nohp  = response.body().getNo_hp();

                txtnama.setText(nama);
                txtemail.setText(email);
                txtnim.setText(nim);
                txtprodi.setText(pro);
                txtadds.setText(addrs);
                txtnohp.setText(nohp);

            }

            @Override
            public void onFailure(Call<ResponseMhs> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
