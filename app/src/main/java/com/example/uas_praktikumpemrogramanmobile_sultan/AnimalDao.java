package com.example.uas_praktikumpemrogramanmobile_sultan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AnimalDao {
    // Untuk tabel endemik
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Animal> animals);

    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    List<Animal> getByType(String tipe);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%'")
    List<Animal> searchByName(String query);

    @Query("SELECT COUNT(*) FROM endemik")
    int getCount();

    // Untuk tabel favorit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteAnimal animal);

    @Delete
    void deleteFavorite(FavoriteAnimal animal);

    @Query("SELECT * FROM favorit")
    List<FavoriteAnimal> getAllFavorites();

    @Query("SELECT * FROM favorit WHERE id = :id LIMIT 1")
    FavoriteAnimal getFavoriteById(String id);
}
