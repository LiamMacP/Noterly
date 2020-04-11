package apps.liamm.noterly.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import apps.liamm.noterly.data.NoterlyRoomDatabase;
import apps.liamm.noterly.data.daos.CategoryDao;
import apps.liamm.noterly.data.entities.CategoryEntity;

public class CategoryRepository {

        private CategoryDao mWordDao;
        private LiveData<List<CategoryEntity>> mAllCategories;

        public CategoryRepository(Application application) {
            NoterlyRoomDatabase db = NoterlyRoomDatabase.getDatabase(application);
            mWordDao = db.categoryDao();
            mAllCategories = mWordDao.getAllCategories();
        }

        public LiveData<List<CategoryEntity>> getAllCategories() {
            return mAllCategories;
        }

        public void insert(CategoryEntity category) {
            NoterlyRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(category));
        }
    }

