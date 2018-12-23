package com.example.rdsaleh.sipu_polindra.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseAPI {

    @FormUrlEncoded
    @POST("registerApi")
    Call<Response> register(
            @Field("nim") String nim,
            @Field("nama_mhs") String nama_mhs,
            @Field("id_prodi") String id_prodi,
            @Field("email") String email,
            @Field("password") String password,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp
    );

    @FormUrlEncoded
    @POST("loginApi")
    Call<Response> login(
            @Field("nim") String nim,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("updateApi/{nim}")
    Call<Response> updateMhs(
            @Field("nim") String nim,
            @Field("nama_mhs") String nama_mhs,
            @Field("id_prodi") String id_prodi,
            @Field("email") String email,
            @Field("password") String password,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp,
            @Path("nim") String pathnim
    );

    @FormUrlEncoded
    @POST("daftarUKM")
    Call<Response> daftarUKM(
            @Field("id_ukm") String id_ukm,
            @Field("nim") String nim,
            @Field("alasan") String alasan
    );

    @GET("getProdi")
    Call<List<AllProdi>> dataPRO();

    @GET("getUKM")
    Call<List<AllUKM>> dataUKM();

    @GET("getMahasiswa/{nim}")
    Call<ResponseMhs> getMhs(
            @Path("nim") String nim
    );

}
