package apps.liamm.noterly.ui.category.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import apps.liamm.noterly.R;

public class CreateCategorySpinnerAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<String> mColours;
    private final int mResource;

    public CreateCategorySpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List colours) {
        super(context, resource, 0, colours);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        mColours = colours;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, parent, false);
        }

        View colourView = view.findViewById(R.id.category_spinner_colour_view);
        colourView.setBackgroundColor(Color.parseColor(mColours.get(position)));

        return view;
    }
}