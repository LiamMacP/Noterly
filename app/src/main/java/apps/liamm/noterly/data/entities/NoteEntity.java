package apps.liamm.noterly.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import apps.liamm.noterly.data.entities.noteitems.BaseItem;
import apps.liamm.noterly.infrastructure.helpers.StringUtils;
import apps.liamm.noterly.infrastructure.typeconverters.DateConverter;
import apps.liamm.noterly.infrastructure.typeconverters.NoteItemConverter;

@Entity(tableName = "note_table")
public class NoteEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "creation_date")
    @TypeConverters(DateConverter.class)
    private Date mCreationDate;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "contents")
    @TypeConverters(NoteItemConverter.class)
    private List<BaseItem> mNoteItems;

    @ColumnInfo(name = "version")
    private int mVersion;

    public NoteEntity() {
        mCreationDate = new Date();
        mTitle = StringUtils.EMPTY;
        mNoteItems = new ArrayList<>();
        mVersion = 1;
    }

    @Ignore
    private NoteEntity(Parcel in) {
        this.mId = in.readInt();
        this.mTitle = in.readString();
        in.readList(mNoteItems, BaseItem.class.getClassLoader());
        this.mVersion = in.readInt();
        this.mCreationDate = DateConverter.fromTimestamp(in.readLong());
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(Date creationDate) {
        mCreationDate = creationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<BaseItem> getNoteItems() {
        return mNoteItems;
    }

    public void setNoteItems(List<BaseItem> noteItems) {
        mNoteItems = noteItems;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeList(mNoteItems);
        dest.writeInt(mVersion);
        dest.writeLong(DateConverter.dateToTimestamp(mCreationDate));
    }
}
