package apps.liamm.noterly.ui.category;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import apps.liamm.noterly.data.entities.CategoryEntity;
import apps.liamm.noterly.repositories.CategoryRepository;

public class CategoryViewModel extends AndroidViewModel {

        private CategoryRepository mRepository;

        private LiveData<List<CategoryEntity>> mAllCategories;

        public CategoryViewModel (Application application) {
            super(application);
            mRepository = new CategoryRepository(application);
            mAllCategories = mRepository.getAllCategories();
        }

        LiveData<List<CategoryEntity>> getAllCategories() { return mAllCategories; }

        public void insert(CategoryEntity category) { mRepository.insert(category); }
}