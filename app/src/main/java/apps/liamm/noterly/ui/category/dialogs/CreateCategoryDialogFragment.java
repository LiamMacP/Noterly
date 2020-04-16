package apps.liamm.noterly.ui.category.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Arrays;
import java.util.List;

import apps.liamm.noterly.R;
import apps.liamm.noterly.data.entities.CategoryEntity;

import static apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogFragment.CreateCategoryResultItem.ACTION;
import static apps.liamm.noterly.ui.category.dialogs.CreateCategoryDialogFragment.CreateCategoryResultItem.CATEGORY;

public class CreateCategoryDialogFragment extends DialogFragment {

    public static String TAG = CreateCategoryDialogFragment.class.getSimpleName();

    private CreateCategoryDialogViewModel mCategoryListViewModel;

    private AlertDialog.Builder mAlertDialogBuilder;


    private Context mContext;
    private boolean mIsEditMode;
    private CategoryEntity mCategory;

    public static class CreateCategoryResultItem {
        public static final String ACTION = "ACTION";
        public static final String CATEGORY = "CATEGORY";
    }

    public CreateCategoryDialogFragment() {
        mIsEditMode = false;
        mCategory = new CategoryEntity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAlertDialogBuilder = new AlertDialog.Builder(mContext);

        mCategoryListViewModel =
                new ViewModelProvider(this).get(CreateCategoryDialogViewModel.class);

        checkForCategory();
    }

    private void checkForCategory() {
        Bundle argumentBundle = getArguments();
        if (argumentBundle != null) {
            CategoryEntity category = CreateCategoryDialogFragmentArgs.fromBundle(argumentBundle).getEditCategory();
            if (category != null) {
                mIsEditMode = true;
                mCategory = category;
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.dialogfragment_category_add, null);

        if (mIsEditMode) {
            mAlertDialogBuilder.setTitle(R.string.create_category_edit_dialog_title);
        } else {
            mAlertDialogBuilder.setTitle(R.string.create_category_create_dialog_title);
        }

        final EditText titleEditText = view.findViewById(R.id.category_add_title_edittext);
        titleEditText.setText(mCategory.getName());

        final Spinner colourSpinner = view.findViewById(R.id.category_add_colour_soinner);
        Resources res = getResources();
        List<String> defaultColours = Arrays.asList(res.getStringArray(R.array.default_colours_array));

        CreateCategorySpinnerAdapter adapter = new CreateCategorySpinnerAdapter(mContext,
                R.layout.dialogfragment_category_spinner_item, defaultColours);
        colourSpinner.setAdapter(adapter);
        int hexLocation = defaultColours.indexOf(mCategory.getColourHex());
        if (hexLocation != -1) {
            colourSpinner.setSelection(hexLocation);
        }

        mAlertDialogBuilder.setView(view);

        mAlertDialogBuilder.setPositiveButton(mIsEditMode ? R.string.update : R.string.add, (dialog, id) -> {
            mCategory.setName(titleEditText.getText().toString());
            mCategory.setColourHex((String) colourSpinner.getSelectedItem());

            mCategoryListViewModel.setDialogResult(CreateCategoryDialogResult.ADD);
            setResult();
        });
        mAlertDialogBuilder.setNegativeButton(R.string.cancel, (dialog, id) ->
                dialog.cancel());

        if (mIsEditMode) {
            mAlertDialogBuilder.setNeutralButton(R.string.delete, (dialog, id) -> {
                mCategoryListViewModel.setDialogResult(CreateCategoryDialogResult.DELETE);
                setResult();
            });
        }

        // Create the AlertDialog object and return it
        return mAlertDialogBuilder.create();
    }

    private void setResult() {
        NavBackStackEntry backStackEntry = NavHostFragment.findNavController(this)
                .getCurrentBackStackEntry();
        if (backStackEntry != null) {
            backStackEntry.getSavedStateHandle().set(CATEGORY, mCategory);
            backStackEntry.getSavedStateHandle().set(ACTION, mCategoryListViewModel.getDialogResult().getValue());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mContext = context;
    }
}
