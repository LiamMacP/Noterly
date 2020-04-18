package apps.liamm.noterly.ui.category.dialogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class CreateCategoryDialogViewModel extends ViewModel {

    private MutableLiveData<CreateCategoryDialogResult> mDialogResult;

    public CreateCategoryDialogViewModel() {
        mDialogResult = new MutableLiveData<>();
    }

    LiveData<CreateCategoryDialogResult> getDialogResult() {
        return mDialogResult;
    }

    void setDialogResult(CreateCategoryDialogResult result) {
        mDialogResult.setValue(result);
    }

}
