package com.todoroo.astrid.ui;

import android.app.Activity;
import android.text.TextUtils;

import com.timsu.astrid.R;
import com.todoroo.andlib.data.Property.StringProperty;
import com.todoroo.astrid.adapter.TaskAdapter;
import com.todoroo.astrid.data.Task;
import com.todoroo.astrid.repeats.RepeatControlSet.RepeatChangedListener;
import com.todoroo.astrid.ui.ImportanceControlSet.ImportanceChangedListener;

public class EditTitleControlSet extends EditTextControlSet implements ImportanceChangedListener, RepeatChangedListener {


    private boolean isRepeating;
    private int importanceValue;

    public EditTitleControlSet(Activity activity, int layout, StringProperty property, int editText) {
        super(activity, layout, property, editText);
    }

    @Override
    public void importanceChanged(int i, int color) {
        importanceValue = i;
        updateCompleteBox();
    }


    @Override
    public void RepeatChanged(boolean repeat) {
        isRepeating = repeat;
        updateCompleteBox();

    }

    @Override
    public void readFromTask(Task task) {
        super.readFromTask(task);
        isRepeating = !TextUtils.isEmpty(task.getValue(Task.RECURRENCE));
        importanceValue = model.getValue(Task.IMPORTANCE);
    }


    public void updateCompleteBox() {
        if(importanceValue < TaskAdapter.IMPORTANCE_RESOURCES.length)
        {
            if (isRepeating)
            {
                completeBox.setButtonDrawable(TaskAdapter.IMPORTANCE_REPEAT_RESOURCES[importanceValue]);
            }
            else
            {
                completeBox.setButtonDrawable(TaskAdapter.IMPORTANCE_RESOURCES[importanceValue]);
            }
        }
        else
        {
            completeBox.setBackgroundResource(R.drawable.btn_check);
        }
    }


}
