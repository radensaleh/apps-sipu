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
import com.example.rdsaleh.sipu_polindra.api.AllUKM;
import com.example.rdsaleh.sipu_polindra.api.ResponseMhs;
import com.example.rdsaleh.sipu_polindra.retroserver.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarUkmActivity extends AppCompatActivity {

    private TextView txtDaftar, txtNamaUKM, txtNotice;
    private Spinner spinUkm;
    private EditText etAlasan;
    private Button btnDaftar, btnBack;
    private Animation uptodown, downtoup;
    private ProgressDialog pd;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ukm);

        mContext = this;
        pd = new ProgressDialog(mContext);

        spinUkm    = (Spinner) findViewById(R.id.spinnerUKM);
        txtNamaUKM = (TextView) findViewById(R.id.txtNamaUKM);
        txtDaftar  = (TextView) findViewById(R.id.txtDaftar);
        txtNotice  = (TextView) findViewById(R.id.txtnotice);
        etAlasan   = (EditText) findViewById(R.id.etAlasan);
        btnBack    = (Button) findViewById(R.id.btnBackToMenu);
        btnDaftar  = (Button) findViewById(R.id.btnDaftar);

        uptodown    = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup    = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        spinUkm.setAnimation(uptodown);
        txtNamaUKM.setAnimation(uptodown);
        etAlasan.setAnimation(uptodown);

        txtDaftar.setAnimation(downtoup);
        btnDaftar.setAnimation(downtoup);
        btnBack.setAnimation(downtoup);
        txtNotice.setAnimation(downtoup);

        dataUKM();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");

                Intent i = new Intent(DaftarUkmActivity.this, DashboardActivity.class);
                i.putExtra("nim", getNim);
                startActivity(i);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNamaUKM.getText() == "Harap Memilih ID UKM"){
                    new AlertDialog.Builder(mContext)
                            .setTitle("Error")
                            .setMessage("Harap Memilih ID UKM")
                            .setCancelable(false)
                            .setIcon(R.drawable.ic_clear_signup_24dp)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }else if(TextUtils.isEmpty(etAlasan.getText())){
                    etAlasan.setError("Alasan Kosong");
                }else{
                    daftarUKM();
                }
            }
        });

    }

    public void dataUKM(){
        Call<List<AllUKM>> call = RetrofitClient
                .getInstance()
                .baseAPI()
                .dataUKM();

        call.enqueue(new Callback<List<AllUKM>>() {
            @Override
            public void onResponse(Call<List<AllUKM>> call, Response<List<AllUKM>> response) {
                if (response.isSuccessful()) {

                    final List<AllUKM> allUKM = response.body();
                    List<String> listSpinner = new ArrayList<>();
                    listSpinner.add(0,"ID UKM");

                    for(int i = 0; i < allUKM.size(); i++){
                        listSpinner.add(allUKM.get(i).getId_ukm());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinUkm.setAdapter(adapter);

                    spinUkm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(parent.getItemAtPosition(position).equals("ID UKM")){
                                txtNamaUKM.setText("Harap Memilih ID UKM");
                            }else{

                                if(position == 1){
                                    txtNamaUKM.setText(allUKM.get(0).getNama_ukm());
                                }else if(position == 2){
                                    txtNamaUKM.setText(allUKM.get(1).getNama_ukm());
                                }else if(position == 3){
                                    txtNamaUKM.setText(allUKM.get(2).getNama_ukm());
                                }else if(position == 4){
                                    txtNamaUKM.setText(allUKM.get(3).getNama_ukm());
                                }else if(position == 5){
                                    txtNamaUKM.setText(allUKM.get(4).getNama_ukm());
                                }else if(position == 6){
                                    txtNamaUKM.setText(allUKM.get(5).getNama_ukm());
                                }else if(position == 7){
                                    txtNamaUKM.setText(allUKM.get(6).getNama_ukm());
                                }else if(position == 8){
                                    txtNamaUKM.setText(allUKM.get(7).getNama_ukm());
                                }else if(position == 9){
                                    txtNamaUKM.setText(allUKM.get(8).getNama_ukm());
                                }else if(position == 10){
                                    txtNamaUKM.setText(allUKM.get(9).getNama_ukm());
                                }else if(position == 11){
                                    txtNamaUKM.setText(allUKM.get(10).getNama_ukm());
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
            public void onFailure(Call<List<AllUKM>> call, Throwable t) {
                Log.e("failure", t.getMessage());
            }
        });
    }

    public void daftarUKM(){
        pd.setIcon(R.drawable.registration);
        pd.setTitle("Registration");
        pd.setMessage("Please Waiting. . .");
        pd.setCancelable(false);
        pd.show();

        Runnable pr = new Runnable() {
            @Override
            public void run() {
                Intent intt = getIntent();
                Bundle b = intt.getExtras();

                String getNim = (String) b.get("nim");
                String getUKM = spinUkm.getSelectedItem().toString();
                String getAls = etAlasan.getText().toString().trim();

                Call<com.example.rdsaleh.sipu_polindra.api.Response> call = RetrofitClient
                        .getInstance()
                        .baseAPI()
                        .daftarUKM(getUKM, getNim, getAls);

//                Log.e("GET UKM", getUKM);
//                Log.e("GET NIM", getNim);
//                Log.e("GET Alsan", getAls);

                call.enqueue(new Callback<com.example.rdsaleh.sipu_polindra.api.Response>() {
                    @Override
                    public void onResponse(Call<com.example.rdsaleh.sipu_polindra.api.Response> call, Response<com.example.rdsaleh.sipu_polindra.api.Response> response) {
                        String error   = response.body().getErrorRes();
                        String message = response.body().getMessageRes();

                        if(error.equals("0")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle(message)
//                            .setMessage(message)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_done_signin_24dp)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent intt = getIntent();
                                            Bundle b = intt.getExtras();

                                            String getNim = (String) b.get("nim");

                                            Intent i = new Intent(DaftarUkmActivity.this, DaftarUkmActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            i.putExtra("nim", getNim);
                                            startActivity(i);

                                        }
                                    }).show();
                        }else if(error.equals("1")){
                            pd.dismiss();
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Failed")
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
                                    .setTitle("Failed")
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

                    }
                });
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(pr, 3000);


    }
}
