package apps.liamm.noterly.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    @NonNull
    private String mName;

    @ColumnInfo(name = "colour")
    @NonNull
    private String mColourHex;

    public CategoryEntity(@NonNull String name,@NonNull  String colourHex) {
        this.mName = name;
        this.mColourHex = colourHex;
    }

    public int getId() {
        return this.mId;
    }

    @NonNull
    public String getName() {
        return this.mName;
    }

    @NonNull
    public String getColourHex() {
        return this.mColourHex;
    }
}
