package apps.liamm.noterly.data.daos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import apps.liamm.noterly.data.entities.CategoryEntity;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryEntity categoryEntity);

    @Query("SELECT * FROM category_table ORDER BY name ASC")
    LiveData<List<CategoryEntity>> getAllCategories();

    @Update
    void updateCategory(CategoryEntity categoryEntity);

    @Query("DELETE FROM category_table")
    void deleteAllCategories();

    @Delete
    void deleteCategory(CategoryEntity categoryEntity);

    @Query("DELETE FROM category_table WHERE name = :name")
    void deleteCategoryByName(@NonNull String name);
}
