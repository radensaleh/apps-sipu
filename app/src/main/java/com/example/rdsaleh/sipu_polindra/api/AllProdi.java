package com.example.rdsaleh.sipu_polindra.api;


public class AllProdi {

    private String id_prodi, id_jurusan, nama_prodi;

    public String getId_prodi() {
        return id_prodi;
    }

    public void setId_prodi(String id_prodi) {
        this.id_prodi = id_prodi;
    }

    public String getId_jurusan() {
        return id_jurusan;
    }

    public void setId_jurusan(String id_jurusan) {
        this.id_jurusan = id_jurusan;
    }

    public String getNama_prodi() {
        return nama_prodi;
    }

    public void setNama_prodi(String nama_prodi) {
        this.nama_prodi = nama_prodi;
    }

    @Override
    public String toString() {
        return "AllProdi{" +
                "id_prodi='" + id_prodi + '\'' +
                ", id_jurusan='" + id_jurusan + '\'' +
                ", nama_prodi='" + nama_prodi + '\'' +
                '}';
    }
}
