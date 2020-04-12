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
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import apps.liamm.noterly.R;
import apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogFragment;

public class CategoryListFragment extends Fragment
        implements CreateCategoryDialogFragment.CreateCategoryDialogListener {

    private CategoryListViewModel mCategoryListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mCategoryListViewModel =
                ViewModelProviders.of(this).get(CategoryListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Fragment fragment = this;

        FloatingActionButton createCategoryFab = view.findViewById(R.id.category_list_add_category);
        createCategoryFab.setOnClickListener(fabView -> {
            // Create an instance of the dialog fragment and show it
            DialogFragment dialog = new CreateCategoryDialogFragment(CategoryListFragment.this);
            dialog.show(getParentFragmentManager(), CreateCategoryDialogFragment.TAG);
        });
    }

    @Override
    public void onDialogAddClick(String text, String colour) {
        Toast.makeText(getContext(), "Text:" + colour, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogCancelClick() {
        Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
    }
}
