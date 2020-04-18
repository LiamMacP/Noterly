package apps.liamm.noterly.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import apps.liamm.noterly.R;

public class NoteListFragment extends Fragment {

    private NoteListViewModel mNoteListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mNoteListViewModel =
                new ViewModelProvider(this).get(NoteListViewModel.class);

        View root = inflater.inflate(R.layout.fragment_note_list, container, false);

        return root;
    }
}
