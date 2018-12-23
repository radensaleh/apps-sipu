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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsaleh.sipu_polindra.api.Response;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;
    private EditText tvNim, tvEmail, tvPassword;
    private Animation uptodown, downtoup;
    private TextView txtsignin;
    private ProgressDialog pd;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        pd = new ProgressDialog(mContext);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin    = (Button) findViewById(R.id.btnLogin);

        tvNim      = (EditText) findViewById(R.id.tvNim);
        tvEmail    = (EditText) findViewById(R.id.tvEmail);
        tvPassword = (EditText) findViewById(R.id.tvPassword);

        txtsignin  = (TextView) findViewById(R.id.txtsignin);

        uptodown    = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup    = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        tvNim.setAnimation(uptodown);
        tvPassword.setAnimation(uptodown);
        tvEmail.setAnimation(uptodown);

        btnLogin.setAnimation(downtoup);
        btnRegister.setAnimation(downtoup);
        txtsignin.setAnimation(downtoup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tvNim.getText())){
                    tvNim.setError("Nim Kosong");
                }else if(TextUtils.isEmpty(tvEmail.getText())){
                    tvEmail.setError("Email Kosong");
                }else if(TextUtils.isEmpty(tvPassword.getText())){
                    tvPassword.setError("Password Kosong");
                }else{
                    login();
                }
            }
        });
    }

    public void login(){
        pd.setIcon(R.drawable.login);
        pd.setTitle("Sign In");
        pd.setMessage("Please Waiting. . .");
        pd.setCancelable(false);
        pd.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {
                String nim        = tvNim.getText().toString().trim();
                String email      = tvEmail.getText().toString().trim();
                String password   = tvPassword.getText().toString().trim();

                Call<Response> call = RetrofitClient
                        .getInstance()
                        .baseAPI()
                        .login(nim,email,password);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        String message = response.body().getMessageRes();
                        String error   = response.body().getErrorRes();

                        if(error.equals("0")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign In Success")
//                            .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_done_signin_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i  = new Intent(LoginActivity.this, DashboardActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            i.putExtra("nim", tvNim.getText().toString());
                                            startActivity(i);

                                            tvNim.setText("");
                                            tvEmail.setText("");
                                            tvPassword.setText("");
                                            tvNim.requestFocus();
                                        }
                                    }).show();
                        }else{
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Sign In Failed")
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
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG);
                    }
                });
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(pr, 3000);


    }
}
