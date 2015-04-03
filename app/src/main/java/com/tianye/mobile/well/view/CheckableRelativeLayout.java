package com.tianye.mobile.well.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * Created by lenovo on 2015/3/19.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
    private static final int[] CheckedStateSet = {android.R.attr.state_checked };
    private boolean checked = false;

    public CheckableRelativeLayout(Context context) {
        this(context,null);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, null);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        //super(context, attrs, defStyleAttr);
        super(context,attrs);
    }


    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        refreshDrawableState();
        forceLayout();
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        checked  =  !checked;
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        //return super.onCreateDrawableState(extraSpace);
        final int[] drawableState = super.onCreateDrawableState(extraSpace +1);
        if(isChecked()){
            mergeDrawableStates(drawableState,CheckedStateSet);
        }
        return drawableState;
    }
}
