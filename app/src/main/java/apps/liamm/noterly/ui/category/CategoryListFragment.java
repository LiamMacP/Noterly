package apps.liamm.noterly.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import apps.liamm.noterly.R;

public class CategoryListFragment extends Fragment {

    private CategoryListViewModel mCategoryListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mCategoryListViewModel =
                ViewModelProviders.of(this).get(CategoryListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category_list, container, false);
        return root;
    }
}
