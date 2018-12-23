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
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsaleh.sipu_polindra.api.AllProdi;
import com.example.rdsaleh.sipu_polindra.api.Response;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class RegisterActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private EditText tvNim, tvNama, tvEmail, tvPassword, tvNoHp, tvAddress;
    private Animation uptodown, downtoup;
    private TextView txtsignup, txtnamaprodi;
    private ProgressDialog pd;
    private Spinner spinProdi;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
        pd = new ProgressDialog(mContext);

        btnLogin    = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        tvNim   = (EditText) findViewById(R.id.tvNim);
        tvNama  = (EditText) findViewById(R.id.tvNama);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        tvPassword = (EditText) findViewById(R.id.tvPassword);
        tvNoHp     = (EditText) findViewById(R.id.tvNoHp);
        tvAddress  = (EditText) findViewById(R.id.tvAddress);

        uptodown    = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup    = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        txtsignup    = (TextView) findViewById(R.id.txtsignup);
        spinProdi    = (Spinner) findViewById(R.id.spinnerProdi);
        txtnamaprodi = (TextView) findViewById(R.id.txtnamaprodi);

        tvNim.setAnimation(uptodown);
        tvNama.setAnimation(uptodown);
        spinProdi.setAnimation(uptodown);
        tvEmail.setAnimation(uptodown);
        tvPassword.setAnimation(uptodown);
        tvNoHp.setAnimation(uptodown);
        tvAddress.setAnimation(uptodown);
        txtnamaprodi.setAnimation(uptodown);

        btnRegister.setAnimation(downtoup);
        btnLogin.setAnimation(downtoup);

        txtsignup.setAnimation(downtoup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        dataProdi();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tvNim.getText())){
                    tvNim.setError("Nim Kosong");
                }else if(TextUtils.isEmpty(tvNama.getText())) {
                    tvNama.setError("Nama Kosong");
                }else if(txtnamaprodi.getText() == "Harap Memilih ID Prodi"){
                    new AlertDialog.Builder(mContext)
                            .setTitle("Error")
                            .setMessage("Harap Memilih ID Prodi")
                            .setCancelable(false)
                            .setIcon(R.drawable.ic_clear_signup_24dp)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }else if(TextUtils.isEmpty(tvEmail.getText())){
                    tvEmail.setError("Email Kosong");
                }else if(TextUtils.isEmpty(tvPassword.getText())){
                    tvPassword.setError("Password Kosong");
                }else if(TextUtils.isEmpty(tvNoHp.getText())){
                    tvNoHp.setError("No HP Kosong");
                }else if(TextUtils.isEmpty(tvAddress.getText())){
                    tvAddress.setError("Alamat Kosong");
                }else{
                    regis();
                }
            }
        });
    }

    public void regis(){
        pd.setIcon(R.drawable.registration);
        pd.setTitle("Sign Up");
        pd.setMessage("Please waiting. . .");
        pd.setCancelable(false);
        pd.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {

                String nim    = tvNim.getText().toString().trim();
                String nama   = tvNama.getText().toString().trim();
                String email  = tvEmail.getText().toString().trim();
                String pass   = tvPassword.getText().toString().trim();
                String alamat = tvAddress.getText().toString().trim();
                String no_hp  = tvNoHp.getText().toString().trim();
                String sProdi = spinProdi.getSelectedItem().toString().trim();

                Call<Response> call = RetrofitClient
                        .getInstance()
                        .baseAPI()
                        .register(nim,nama,sProdi,email,pass,alamat,no_hp);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        String message = response.body().getMessageRes();
                        String error   = response.body().getErrorRes();

                        if(error.equals("0")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign Up Success")
//                            .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_done_signin_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
//                                    tvNim.setText("");
//                                    tvNama.setText("");
//                                    tvEmail.setText("");
//                                    tvPassword.setText("");
//                                    tvAddress.setText("");
//                                    tvNoHp.setText("");
//
//                                    txtnamaprodi.setText("Harap Memilih ID Prodi");
//                                    spinProdi.getSelectedItemPosition();
                                            Intent i = new Intent(RegisterActivity.this, RegisterActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(i);

//                                    tvNim.requestFocus();
                                        }
                                    }).show();
                        }else if(error.equals("1")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign Up Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(error.equals("2")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign Up Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(error.equals("3")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign Up Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }else if(error.equals("4")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign Up Failed")
                                    .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_clear_signup_24dp)
                                    .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);
                    }
                });
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(pr, 3000);

    }

      public void dataProdi(){
            Call<List<AllProdi>> call = RetrofitClient
                    .getInstance()
                    .baseAPI()
                    .dataPRO();
            call.enqueue(new Callback<List<AllProdi>>() {
                @Override
                public void onResponse(Call<List<AllProdi>> call, final retrofit2.Response<List<AllProdi>> response) {
                    if (response.isSuccessful()) {

                        final List<AllProdi> allPRO = response.body();
                        List<String> listSpinner = new ArrayList<>();
                        listSpinner.add(0,"ID Prodi");

                        for(int i = 0; i < allPRO.size(); i++){
                            listSpinner.add(allPRO.get(i).getId_prodi());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProdi.setAdapter(adapter);

                        spinProdi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                if(parent.getItemAtPosition(position).equals("ID Prodi")){
                                      txtnamaprodi.setText("Harap Memilih ID Prodi");
                                }else{

                                      if(position == 1){
                                          txtnamaprodi.setText(allPRO.get(0).getNama_prodi());
                                      }else if(position == 2){
                                          txtnamaprodi.setText(allPRO.get(1).getNama_prodi());
                                      }else if(position == 3){
                                          txtnamaprodi.setText(allPRO.get(2).getNama_prodi());
                                      }else if(position == 4){
                                          txtnamaprodi.setText(allPRO.get(3).getNama_prodi());
                                      }else if(position == 5){
                                          txtnamaprodi.setText(allPRO.get(4).getNama_prodi());
                                      }else if(position == 6){
                                          txtnamaprodi.setText(allPRO.get(5).getNama_prodi());
                                      }else if(position == 7){
                                          txtnamaprodi.setText(allPRO.get(6).getNama_prodi());
                                      }else if(position == 8){
                                          txtnamaprodi.setText(allPRO.get(7).getNama_prodi());
                                      }else if(position == 9){
                                          txtnamaprodi.setText(allPRO.get(8).getNama_prodi());
                                      }else if(position == 10){
                                          txtnamaprodi.setText(allPRO.get(9).getNama_prodi());
                                      }else if(position == 11){
                                          txtnamaprodi.setText(allPRO.get(10).getNama_prodi());
                                      }
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    } else {
                        Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<AllProdi>> call, Throwable t) {
                    Log.e("failure", t.getMessage());
                }
            });
      }
}
