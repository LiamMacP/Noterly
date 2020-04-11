package apps.liamm.noterly.ui.category.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import apps.liamm.noterly.R;

public class CreateCategoryDialogFragment extends DialogFragment {

    public static String TAG = CreateCategoryDialogFragment.class.getSimpleName();
    private CreateCategoryDialogListener mListener;

    public CreateCategoryDialogFragment(CreateCategoryDialogListener listener) {
        this.mListener = listener;
    }

    public interface CreateCategoryDialogListener {
        void onDialogAddClick(String text, long selectedItemId);
        void onDialogCancelClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_category);

        Context context = getActivity();
        View view = View.inflate(context, R.layout.dialogfragment_category_add, null);
        final EditText titleEditText = view.findViewById(R.id.category_add_title_edittext);
        final Spinner colourSpinner = view.findViewById(R.id.category_add_colour_soinner);

        builder.setView(view);

        builder.setPositiveButton(R.string.add, (dialog, id) ->
                mListener.onDialogAddClick(titleEditText.getText().toString(), colourSpinner.getSelectedItemId()));
        builder.setNegativeButton(R.string.cancel, (dialog, id) ->
                mListener.onDialogCancelClick());

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
