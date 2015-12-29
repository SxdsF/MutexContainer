package com.sxdsf.mutexcontainer.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.sxdsf.mutexcontainer.listener.MutexOnTouchListener;
import com.sxdsf.mutexcontainer.listener.OnItemSelectedChangedListener;

/**
 * Created by sunbowen on 2015/12/29.
 */
public class MutexGridLayout extends GridLayout {

    private OnItemSelectedChangedListener onItemSelectedChangedListener;
    private final OnTouchListener onTouchListener;

    public void setOnItemSelectedChangedListener(OnItemSelectedChangedListener onItemSelectedChangedListener) {
        this.onItemSelectedChangedListener = onItemSelectedChangedListener;
    }

    public MutexGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.onTouchListener = new MutexOnTouchListener(context, this, attrs, this.onItemSelectedChangedListener);
    }

    public MutexGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onTouchListener = new MutexOnTouchListener(context, this, attrs, this.onItemSelectedChangedListener);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        child.setOnTouchListener(this.onTouchListener);
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        child.setOnTouchListener(this.onTouchListener);
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        child.setOnTouchListener(this.onTouchListener);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        child.setOnTouchListener(this.onTouchListener);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        child.setOnTouchListener(this.onTouchListener);
    }
}
