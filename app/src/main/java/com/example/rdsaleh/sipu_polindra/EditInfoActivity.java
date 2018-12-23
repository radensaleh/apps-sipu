package com.example.rdsaleh.sipu_polindra;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsaleh.sipu_polindra.api.ResponseMhs;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInfoActivity extends AppCompatActivity {

    private Button btnSubmit, btnBack;
    private EditText tvNim, tvNama, tvEmail, tvProdi, tvPassword, tvNoHp, tvAddress;
    private Animation uptodown, downtoup;
    private TextView txtEditInfo, txtnamaprodi;
    private Spinner spinProdi;
    private ProgressDialog pd;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        mContext = this;
        pd = new ProgressDialog(mContext);

        btnSubmit   = (Button) findViewById(R.id.btnSubmit);
        btnBack     = (Button) findViewById(R.id.btnBackToMenu);

        tvNim   = (EditText) findViewById(R.id.tvNim);
        tvNama  = (EditText) findViewById(R.id.tvNama);
        tvProdi = (EditText) findViewById(R.id.tvProdi);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        tvPassword = (EditText) findViewById(R.id.tvPassword);
        tvNoHp     = (EditText) findViewById(R.id.tvNoHp);
        tvAddress  = (EditText) findViewById(R.id.tvAddress);

        uptodown    = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup    = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        txtEditInfo = (TextView) findViewById(R.id.txtEditInfo);

//        spinProdi    = (Spinner) findViewById(R.id.spinnerProdi);
//        txtnamaprodi = (TextView) findViewById(R.id.txtnamaprodi);


        tvNim.setAnimation(uptodown);
        tvNama.setAnimation(uptodown);
        tvProdi.setAnimation(uptodown);
//        txtnamaprodi.setAnimation(uptodown);
//        spinProdi.setAnimation(uptodown);
        tvEmail.setAnimation(uptodown);
        tvPassword.setAnimation(uptodown);
        tvNoHp.setAnimation(uptodown);
        tvAddress.setAnimation(uptodown);

        btnSubmit.setAnimation(downtoup);
        btnBack.setAnimation(downtoup);

        txtEditInfo.setAnimation(downtoup);

        dataMhs();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(EditInfoActivity.this, DashboardActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tvNim.getText())){
                    tvNim.setError("Nim Kosong");
                }else if(TextUtils.isEmpty(tvNama.getText())){
                    tvNama.setError("Nama Kosong");
                }else if(TextUtils.isEmpty(tvProdi.getText())){
                    tvProdi.setError("Prodi Kosong");
                }else if(TextUtils.isEmpty(tvEmail.getText())){
                    tvEmail.setError("Email Kosong");
                }else if(TextUtils.isEmpty(tvPassword.getText())){
                    tvPassword.setError("Password Kosong");
                }else if(TextUtils.isEmpty(tvNoHp.getText())){
                    tvNoHp.setError("No HP Kosong");
                }else if(TextUtils.isEmpty(tvAddress.getText())){
                    tvAddress.setError("Alamat Kosong");
                }else{
                    update();
                }
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
                String email = response.body().getEmail();
                String addrs = response.body().getAlamat();
                String nohp  = response.body().getNo_hp();

                tvNim.setText(nim);
                tvNama.setText(nama);
                tvProdi.setText(pro);
                tvEmail.setText(email);
                tvNoHp.setText(nohp);
                tvAddress.setText(addrs);

                tvProdi.setEnabled(false);
                tvNim.setEnabled(false);
            }

            @Override
            public void onFailure(Call<ResponseMhs> call, Throwable t) {
                Toast.makeText(EditInfoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void update(){
        pd.setIcon(R.drawable.boy);
        pd.setTitle("Update Data");
        pd.setMessage("Please Waiting. . .");
        pd.setCancelable(false);
        pd.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {
                String nim    = tvNim.getText().toString().trim();
                String nama   = tvNama.getText().toString().trim();
                String prodi  = tvProdi.getText().toString().trim();
                String email  = tvEmail.getText().toString().trim();
                String pass   = tvPassword.getText().toString().trim();
                String alamat = tvAddress.getText().toString().trim();
                String no_hp  = tvNoHp.getText().toString().trim();

                String pathnim    = tvNim.getText().toString().trim();

                Call<com.example.rdsaleh.sipu_polindra.api.Response> call = RetrofitClient
                        .getInstance()
                        .baseAPI()
                        .updateMhs(nim,nama,prodi,email,pass,alamat,no_hp,pathnim);

                call.enqueue(new Callback<com.example.rdsaleh.sipu_polindra.api.Response>() {
                    @Override
                    public void onResponse(Call<com.example.rdsaleh.sipu_polindra.api.Response> call, retrofit2.Response<com.example.rdsaleh.sipu_polindra.api.Response> response) {
                        String error   = response.body().getErrorRes();
                        String message = response.body().getMessageRes();

                        if(error.equals("0")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Update Success")
//                            .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_done_signin_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent intt = getIntent();
                                            Bundle b = intt.getExtras();

                                            String getNim = (String) b.get("nim");

                                            Intent i = new Intent(EditInfoActivity.this, ProfileActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            i.putExtra("nim", getNim);
                                            startActivity(i);
                                        }
                                    }).show();
                        }else if(error.equals("1")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Update Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(error.equals("2")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Update Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(error.equals("3")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Update Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<com.example.rdsaleh.sipu_polindra.api.Response> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);
                    }
                });
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(pr, 3000);


    }
}
