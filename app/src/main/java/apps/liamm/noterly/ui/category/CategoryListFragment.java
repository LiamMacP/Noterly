package apps.liamm.noterly.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import apps.liamm.noterly.R;
import apps.liamm.noterly.data.entities.CategoryEntity;
import apps.liamm.noterly.infrastructure.helpers.ColourUtils;
import apps.liamm.noterly.infrastructure.helpers.StringUtils;
import apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogFragment;

public class CategoryListFragment extends Fragment
        implements CreateCategoryDialogFragment.CreateCategoryDialogListener {

    private CategoryListViewModel mCategoryListViewModel;
    private CategoryListAdapter mCategoryListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mCategoryListAdapter = new CategoryListAdapter(getContext());

        mCategoryListViewModel =
                new ViewModelProvider(this).get(CategoryListViewModel.class);

        mCategoryListViewModel.getAllCategories().observe(getViewLifecycleOwner(),
                categoryEntities -> mCategoryListAdapter.setCategories(categoryEntities));

        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton createCategoryFab = view.findViewById(R.id.category_list_add_category);
        createCategoryFab.setOnClickListener(fabView -> {
            // Create an instance of the dialog fragment and show it
            DialogFragment dialog = new CreateCategoryDialogFragment(CategoryListFragment.this);
            dialog.show(getParentFragmentManager(), CreateCategoryDialogFragment.TAG);
        });

        RecyclerView categoryListRecyclerView = view.findViewById(R.id.category_list_recycler_view);
        categoryListRecyclerView.setAdapter(mCategoryListAdapter);
        categoryListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDialogAddClick(String text, String colour) {
        if (StringUtils.IsNullOrEmpty(text)  || !ColourUtils.IsHexString(colour)) {
            Toast.makeText(getContext(), R.string.add_category_input_invalid,
                    Toast.LENGTH_LONG).show();
            return;
        }

        CategoryEntity categoryEntity = new CategoryEntity(text, colour);
        mCategoryListViewModel.insert(categoryEntity);
    }

    @Override
    public void onDialogCancelClick() {
        Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
    }
}
