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
import apps.liamm.noterly.ui.category.listeners.OnItemClickListener;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<CategoryEntity> mCategories;
    private OnItemClickListener mClickListener;

    CategoryListAdapter(@NonNull Context context, @NonNull OnItemClickListener onItemClickListener) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mClickListener = onItemClickListener;
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

            holder.bindData(category, mClickListener);
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
        private View itemView;
        private View colourView;
        private TextView categoryTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            this.colourView = itemView.findViewById(R.id.category_list_item_colour_view);
            this.categoryTextView = itemView.findViewById(R.id.category_list_item_title_text_view);
        }

        void bindData(@NonNull CategoryEntity category, @NonNull OnItemClickListener clickListener) {
            colourView.setBackgroundColor(Color.parseColor(category.getColourHex()));
            categoryTextView.setText(category.getName());

            itemView.setOnClickListener(v -> clickListener.onItemClicked(category));
        }
    }
}
