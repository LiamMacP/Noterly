package apps.liamm.noterly.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

import apps.liamm.noterly.infrastructure.helpers.StringUtils;

@Entity(tableName = "category_table")
public class CategoryEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    @NonNull
    private String mName;

    @ColumnInfo(name = "colour")
    @NonNull
    private String mColourHex;

    @Ignore
    public CategoryEntity() {
        this.mName = StringUtils.EMPTY;
        this.mColourHex = StringUtils.EMPTY;
    }

    public CategoryEntity(@NonNull String name, @NonNull  String colourHex) {
        this.mName = name;
        this.mColourHex = colourHex;
    }

    @Ignore
    protected CategoryEntity(Parcel in) {
        mId = in.readInt();
        mName = Objects.requireNonNull(in.readString());
        mColourHex = Objects.requireNonNull(in.readString());
    }

    public static final Creator<CategoryEntity> CREATOR = new Creator<CategoryEntity>() {
        @Override
        public CategoryEntity createFromParcel(Parcel in) {
            return new CategoryEntity(in);
        }

        @Override
        public CategoryEntity[] newArray(int size) {
            return new CategoryEntity[size];
        }
    };

    public void setId(int id) {
        this.mId = id;
    }

    public int getId() {
        return this.mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @NonNull
    public String getColourHex() {
        return mColourHex;
    }

    public void setColourHex(@NonNull String colourHex) {
        mColourHex = colourHex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mColourHex);
    }
}
