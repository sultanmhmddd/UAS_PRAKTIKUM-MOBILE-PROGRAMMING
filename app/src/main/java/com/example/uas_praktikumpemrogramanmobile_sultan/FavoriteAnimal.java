package com.example.uas_praktikumpemrogramanmobile_sultan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "favorit")
public class FavoriteAnimal implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String tipe;
    private String nama;
    private String namaLatin;
    private String deskripsi;
    private String asal;
    private String foto;
    private String status;

    // Constructor to convert from Animal to FavoriteAnimal
    public FavoriteAnimal() {}

    public FavoriteAnimal(Animal animal) {
        this.id = animal.getId();
        this.tipe = animal.getTipe();
        this.nama = animal.getNama();
        this.namaLatin = animal.getNamaLatin();
        this.deskripsi = animal.getDeskripsi();
        this.asal = animal.getAsal();
        this.foto = animal.getFoto();
        this.status = animal.getStatus();
    }

    // Getters and Setters
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
