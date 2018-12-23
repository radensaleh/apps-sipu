package com.example.rdsaleh.sipu_polindra;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsaleh.sipu_polindra.api.ResponseMhs;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardActivity extends AppCompatActivity {

    private CardView cvKompa, cvKopen, cvRpi, cvPopi, cvFolafo, cvDaftar;
    private TextView txthello, txtnama, txtnimprodi;
    private Button btnMyInfo, btnEditInfo, btnLogout;
    private Animation uptodown, downtoup, rightoleft, lefttoright;
    Context mContext;

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(mContext)
                .setTitle("Alert")
                .setMessage("Anda Ingin Logout ? ")
                .setCancelable(false)
                .setIcon(R.drawable.ic_warning_logout_24dp)
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mContext = this;

        cvKompa  = (CardView) findViewById(R.id.cvKompa);
        cvKopen  = (CardView) findViewById(R.id.cvKopen);
        cvRpi    = (CardView) findViewById(R.id.cvRpi);
        cvPopi   = (CardView) findViewById(R.id.cvPopi);
        cvFolafo = (CardView) findViewById(R.id.cvFolafo);
        cvDaftar = (CardView) findViewById(R.id.cvDaftar);

        txthello    = (TextView) findViewById(R.id.txthello);
        txtnama     = (TextView) findViewById(R.id.txtnama);
        txtnimprodi = (TextView) findViewById(R.id.txtnimprodi);

        btnMyInfo   = (Button) findViewById(R.id.btnMyInfo);
        btnEditInfo = (Button) findViewById(R.id.btnEditInfo);
        btnLogout   = (Button) findViewById(R.id.btnLogout);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        rightoleft  = AnimationUtils.loadAnimation(this,R.anim.righttoleft);

        txthello.setAnimation(uptodown);
        txtnama.setAnimation(uptodown);
        txtnimprodi.setAnimation(uptodown);
        btnMyInfo.setAnimation(uptodown);
        btnEditInfo.setAnimation(uptodown);
        btnLogout.setAnimation(uptodown);
        cvKompa.setAnimation(rightoleft);
        cvKopen.setAnimation(lefttoright);
        cvRpi.setAnimation(lefttoright);
        cvPopi.setAnimation(rightoleft);
        cvFolafo.setAnimation(rightoleft);
        cvDaftar.setAnimation(lefttoright);

        Mhs();

        cvKompa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, KompaActivity.class);
                startActivity(i);
            }
        });

        cvKopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, KopenActivity.class);
                startActivity(i);
            }
        });

        cvRpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, RpiActivity.class);
                startActivity(i);
            }
        });

        cvPopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, PopiActivity.class);
                startActivity(i);
            }
        });

        cvFolafo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, FolafoActivity.class);
                startActivity(i);
            }
        });

        cvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(DashboardActivity.this, DaftarUkmActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });

        btnMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(DashboardActivity.this, ProfileActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(DashboardActivity.this, EditInfoActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Alert")
                        .setMessage("Anda Ingin Logout ? ")
                        .setCancelable(false)
                        .setIcon(R.drawable.ic_warning_logout_24dp)
                        .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }
                        }).show();
            }
        });
    }

    public void Mhs(){

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
                String mail = response.body().getEmail();

                txtnimprodi.setText(nim + " | " + mail);
                txtnama.setText(nama);

            }

            @Override
            public void onFailure(Call<ResponseMhs> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
