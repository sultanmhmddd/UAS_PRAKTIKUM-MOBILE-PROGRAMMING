package com.example.uas_praktikumpemrogramanmobile_sultan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "endemik")
public class Animal implements Serializable {
    
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("tipe")
    private String tipe; // Hewan atau Tumbuhan

    @SerializedName("nama")
    private String nama;

    @SerializedName("nama_latin")
    private String namaLatin;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("asal")
    private String asal;

    @SerializedName("foto")
    private String foto;

    @SerializedName("status")
    private String status;

    // Getters and Setters (Room butuh Setters atau Constructor)
    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNamaLatin() { return namaLatin; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getAsal() { return asal; }
    public void setAsal(String asal) { this.asal = asal; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
