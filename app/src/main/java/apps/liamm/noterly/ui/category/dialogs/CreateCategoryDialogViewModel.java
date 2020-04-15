package apps.liamm.noterly.ui.category.dialogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateCategoryDialogViewModel extends ViewModel {

    private MutableLiveData<CreateCategoryDialogResult> mDialogResult;

    public CreateCategoryDialogViewModel() {
        mDialogResult = new MutableLiveData<>();
    }

    public LiveData<CreateCategoryDialogResult> getDialogResult() {
        return mDialogResult;
    }

    public void setDialogResult(CreateCategoryDialogResult result) {
        mDialogResult.setValue(result);
    }

}
