package apps.liamm.noterly.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import apps.liamm.noterly.R;
import apps.liamm.noterly.data.entities.noteitems.BaseItem;
import apps.liamm.noterly.data.entities.noteitems.TextItem;
import apps.liamm.noterly.infrastructure.typeconverters.NoteItemConverter;

class NoteListFragment extends Fragment {

    private NoteListViewModel mNoteListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mNoteListViewModel =
                new ViewModelProvider(this).get(NoteListViewModel.class);

        View root = inflater.inflate(R.layout.fragment_note_list, container, false);

        List<BaseItem> list = new ArrayList<>();
        list.add(new TextItem("hehehe"));

        String test = null;
        try {
            test = NoteItemConverter.toXmlString(list);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            List<BaseItem> test1 = NoteItemConverter.toBaseItems(test);

            return root;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return root;
    }
}
