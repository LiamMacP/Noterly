package apps.liamm.noterly.ui.category;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.liamm.noterly.R;
import apps.liamm.noterly.data.entities.CategoryEntity;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<CategoryEntity> mCategories;

    CategoryListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mCategories != null) {
            CategoryEntity category = mCategories.get(position);
            holder.colourView.setBackgroundColor(Color.parseColor(category.getColourHex()));
            holder.categoryTextView.setText(category.getName());
        }
    }

    @Override
    public int getItemCount() {
        return this.mCategories == null ? 0 : this.mCategories.size();
    }

    void setCategories(List<CategoryEntity> categories){
        this.mCategories = categories;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View colourView;
        TextView categoryTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            colourView = itemView.findViewById(R.id.category_list_item_colour_view);
            categoryTextView = itemView.findViewById(R.id.category_list_item_title_text_view);
        }
    }
}
