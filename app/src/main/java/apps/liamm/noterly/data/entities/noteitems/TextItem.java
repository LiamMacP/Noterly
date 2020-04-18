package apps.liamm.noterly.data.entities.noteitems;

import android.os.Parcel;
import android.os.Parcelable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import apps.liamm.noterly.infrastructure.helpers.StringUtils;

public class TextItem extends BaseItem {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TextItem createFromParcel(Parcel in) {
            return new TextItem(in);
        }

        public TextItem[] newArray(int size) {
            return new TextItem[size];
        }
    };

    private String mContent;

    public TextItem() {
        super(NoteItemType.TEXT);

        this.mContent = StringUtils.EMPTY;
    }

    public TextItem(String content) {
        super(NoteItemType.TEXT);

        this.mContent = content;
    }

    private TextItem(Parcel in) {
        super(NoteItemType.TEXT);

        mContent = in.readString();
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mContent);
    }
}
