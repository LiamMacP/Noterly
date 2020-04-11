package apps.liamm.noterly.ui.category.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

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
        public void onDialogAddClick(DialogFragment dialog);
        public void onDialogCancelClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_category);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setView(R.layout.dialogfragment_category_add);
        } else {
            Context context = getActivity();
            if (context != null) {
                View view = View.inflate(context, R.layout.dialogfragment_category_add, null);
                builder.setView(view);
            }
        }

        builder.setPositiveButton(R.string.add, (dialog, id) ->
                mListener.onDialogAddClick(CreateCategoryDialogFragment.this));
        builder.setNegativeButton(R.string.cancel, (dialog, id) ->
                mListener.onDialogCancelClick(CreateCategoryDialogFragment.this));

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
