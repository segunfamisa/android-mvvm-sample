package com.segunfamisa.sample.mvvm.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

public class PosterImageView extends ForegroundImageView {

    public PosterImageView(Context context) {
        super(context);
    }

    public PosterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int preferredWidth = width;
        int preferredHeight = height;
        if (width >= height) {
            preferredWidth = (2 * height) / 3;
        } else {
            preferredHeight = (3 * width) / 2;
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(preferredWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(preferredHeight, MeasureSpec.EXACTLY));
    }
}
