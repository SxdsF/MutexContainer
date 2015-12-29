package com.sxdsf.mutexcontainer.listener;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sxdsf.mutexcontainer.R;

/**
 * Created by sunbowen on 2015/12/29.
 */
public class MutexOnTouchListener implements View.OnTouchListener {

    private final ViewGroup container;
    private final OnItemSelectedChangedListener listener;
    private boolean enableReverse;
    private int selectedMode;


    private static final int SELECTED = 0;
    private static final int ACTIVATED = 1;

    public MutexOnTouchListener(Context context, ViewGroup container, AttributeSet attrs, OnItemSelectedChangedListener listener) {
        this.container = container;
        this.listener = listener;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.mutex);
        this.enableReverse = ta.getBoolean(R.styleable.mutex_enable_reverse, false);
        this.selectedMode = ta.getInteger(R.styleable.mutex_select_mode, SELECTED);
        ta.recycle();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            boolean isSelected = false;
            switch (this.selectedMode) {
                case SELECTED:
                    isSelected = v.isSelected();
                    break;
                case ACTIVATED:
                    isSelected = v.isActivated();
                    break;
            }

            if (isSelected) {
                if (this.enableReverse) {
                    this.resetAll();
                    if (this.listener != null) {
                        this.listener.onItemSelectedChanged(v, false);
                    }
                }
            } else {
                this.resetAll();
                boolean selected = false;
                switch (this.selectedMode) {
                    case SELECTED:
                        v.setSelected(true);
                        selected = v.isSelected();
                        break;
                    case ACTIVATED:
                        v.setActivated(false);
                        selected = v.isActivated();
                        break;
                }
                if (this.listener != null) {
                    this.listener.onItemSelectedChanged(v, selected);
                }
            }
        }
        return false;
    }

    private void resetAll() {
        int count = this.container.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = this.container.getChildAt(i);
            if (view != null) {
                switch (this.selectedMode) {
                    case SELECTED:
                        view.setSelected(false);
                        break;
                    case ACTIVATED:
                        view.setActivated(false);
                        break;
                }
            }
        }
    }
}
