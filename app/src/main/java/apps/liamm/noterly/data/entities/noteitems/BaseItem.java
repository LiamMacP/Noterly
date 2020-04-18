package apps.liamm.noterly.data.entities.noteitems;

import android.os.Parcelable;

import org.w3c.dom.Element;

public abstract class BaseItem implements Parcelable {
    public enum NoteItemType {
        TEXT,
        CHECKLIST,
        PICTURE
    }

    private NoteItemType mType;

    BaseItem(NoteItemType type) {
        this.mType = type;
    }

    public NoteItemType getType() {
        return mType;
    }

    public void setType(NoteItemType type) {
        mType = type;
    }
}
