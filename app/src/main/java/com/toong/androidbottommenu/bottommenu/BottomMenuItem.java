package com.toong.androidbottommenu.bottommenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.toong.androidbottommenu.R;

public class BottomMenuItem extends LinearLayout {
    private ImageView imgImage;
    private TextView tvTitle;

    private Drawable normalImage;
    private Drawable selectedImage;

    private int normalTitleColor;
    private int selectedTitleColor;

    public BottomMenuItem(Context context) {
        this(context, null);
    }

    public BottomMenuItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMenuItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_menu_item, this, true);

        imgImage = (ImageView) findViewById(R.id.image);
        tvTitle = (TextView) findViewById(R.id.text_title);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BottomMenuItemStyle);
        String text = ta.getString(R.styleable.BottomMenuItemStyle_title);

        normalImage = ta.getDrawable(R.styleable.BottomMenuItemStyle_normalImage);
        selectedImage = ta.getDrawable(R.styleable.BottomMenuItemStyle_selectedImage);

        normalTitleColor = ta.getColor(R.styleable.BottomMenuItemStyle_normalTitleColor,
                Color.parseColor("#444444")); // default color is gray
        selectedTitleColor = ta.getColor(R.styleable.BottomMenuItemStyle_selectedTitleColor,
                Color.parseColor("#0089D0")); //  default selected color is blue

        ta.recycle();
        if (normalImage != null) {
            imgImage.setImageDrawable(normalImage);
        }
        tvTitle.setText(text);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            imgImage.setImageDrawable(selectedImage);
            tvTitle.setTextColor(selectedTitleColor);
            return;
        }
        imgImage.setImageDrawable(normalImage);
        tvTitle.setTextColor(normalTitleColor);
    }

    public String getTitle() {
        return tvTitle.getText().toString();
    }
}