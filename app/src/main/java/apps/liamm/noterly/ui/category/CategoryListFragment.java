package apps.liamm.noterly.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import apps.liamm.noterly.R;
import apps.liamm.noterly.data.entities.CategoryEntity;
import apps.liamm.noterly.infrastructure.helpers.ColourUtils;
import apps.liamm.noterly.infrastructure.helpers.StringUtils;
import apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogFragment;
import apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogResult;
import apps.liamm.noterly.ui.category.listeners.OnItemClickListener;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class CategoryListFragment extends Fragment
        implements OnItemClickListener {

    private CategoryListViewModel mCategoryListViewModel;
    private CategoryListAdapter mCategoryListAdapter;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mCategoryListAdapter = new CategoryListAdapter(mContext, CategoryListFragment.this);

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
            CategoryListFragmentDirections.ActionOpenCreateCategory action =
                    CategoryListFragmentDirections.actionOpenCreateCategory();
            Navigation.findNavController(fabView).navigate(action);
            setupDialogListener();
        });

        RecyclerView categoryListRecyclerView = view.findViewById(R.id.category_list_recycler_view);
        categoryListRecyclerView.setAdapter(mCategoryListAdapter);
        categoryListRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void addCategory(CategoryEntity category) {
        if (StringUtils.IsNullOrEmpty(category.getName()) || !ColourUtils.IsHexString(category.getColourHex())) {
            Toast.makeText(mContext, R.string.add_category_input_invalid,
                    Toast.LENGTH_LONG).show();
            return;
        }

        mCategoryListViewModel.insert(category);
    }

    private void deleteCategory(CategoryEntity category) {
        mCategoryListViewModel.delete(category);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mContext = null;
    }

    @Override
    public void onItemClicked(CategoryEntity category) {
        CategoryListFragmentDirections.ActionOpenCreateCategory action =
                CategoryListFragmentDirections.actionOpenCreateCategory();
        action.setEditCategory(category);
        NavHostFragment.findNavController(this).navigate(action);
        setupDialogListener();
    }

    private void setupDialogListener() {
        NavBackStackEntry navBackStackEntry = findNavController(this)
                .getCurrentBackStackEntry();

        if (navBackStackEntry != null) {
            SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();

            MutableLiveData<CreateCategoryDialogResult>  dialogResult =
                    savedStateHandle.getLiveData(CreateCategoryDialogFragment.CreateCategoryResultItem.ACTION, CreateCategoryDialogResult.NONE);

            dialogResult.observe(getViewLifecycleOwner(), createCategoryDialogResult -> {
                if (createCategoryDialogResult.equals(CreateCategoryDialogResult.ADD)) {
                    addCategory(Objects.requireNonNull(savedStateHandle.get(CreateCategoryDialogFragment.CreateCategoryResultItem.CATEGORY)));
                } else if (createCategoryDialogResult.equals(CreateCategoryDialogResult.DELETE)) {
                    deleteCategory(Objects.requireNonNull(savedStateHandle.get(CreateCategoryDialogFragment.CreateCategoryResultItem.CATEGORY)));
                }
            });
        }
    }
}
