package com.toong.androidbottommenu.bottommenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.toong.androidbottommenu.R;

/**
 * Created by PhanVanLinh on 05/07/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class BottomMenu extends LinearLayout {
    OnMenuItemClickListener mOnMenuItemClickListener;
    OnMenuChanged mOnMenuChanged;
    BottomMenuItem menuItemArray[];
    private int selectedMenuPosition;

    public BottomMenu(Context context) {
        this(context, null);
    }

    public BottomMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_menu, this, true);
        initMenu();
        handleMenuItemClick();
    }

    private void initMenu() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_group_menu);
        menuItemArray = new BottomMenuItem[linearLayout.getChildCount()];
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            menuItemArray[i] = (BottomMenuItem) linearLayout.getChildAt(i);
        }
    }

    private void handleMenuItemClick() {
        for (int i = 0; i < menuItemArray.length; i++) {
            final int finalI = i;
            menuItemArray[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                    if (mOnMenuItemClickListener != null) {
                        mOnMenuItemClickListener.onMenuItemClick(finalI);
                    }
                    select(finalI);
                }
            });
        }
    }

    public void select(int position) {
        reset();
        getMenuItem(position).setSelected(true);
        selectedMenuPosition = position;
        if (mOnMenuChanged != null) {
            mOnMenuChanged.onMenuChanged();
        }
    }

    private void reset() {
        for (int i = 0; i < menuItemArray.length; i++) {
            getMenuItem(i).setSelected(false);
        }
    }

    public int getSelectedMenuPosition() {
        return selectedMenuPosition;
    }

    public String[] getAllMenuTitle() {
        String[] menuItemTitleArray = new String[menuItemArray.length];
        for (int i = 0; i < menuItemTitleArray.length; i++) {
            menuItemTitleArray[i] = getMenuItemTitle(i);
        }
        return menuItemTitleArray;
    }

    public String getMenuItemTitle(int position) {
        return getMenuItem(position).getTitle();
    }

    public String getSelectedMenuItemTitle() {
        return getMenuItemTitle(getSelectedMenuPosition());
    }

    public BottomMenuItem getMenuItem(int position) {
        return menuItemArray[position];
    }

    public int size() {
        return menuItemArray.length;
    }

    public void setOnItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOnMenuChanged(OnMenuChanged onMenuChanged) {
        mOnMenuChanged = onMenuChanged;
    }

    public void showBadge(int position, int badge) {
        getMenuItem(position).setBadge(badge);
    }

    /**
     * Callback when we click a menu item
     */
    public interface OnMenuItemClickListener {
        void onMenuItemClick(int position);
    }

    /**
     * Callback when we select a menu item
     */
    public interface OnMenuChanged {
        void onMenuChanged();
    }
}
