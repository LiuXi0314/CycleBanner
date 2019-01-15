package com.liuxi.cyclebanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author liuxi
 * @Email xiaoxixizhizhi@gmail.com
 */
public class PageIndicator extends View {

    private final Paint mPaintUnselected = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCurrentPage = Constants.INVALID_PARAMS;
    private int mTotalPage;
    private int mLineWidth;
    private int mStrokeWidth;
    private int mSelectedColor = 0xFFFFFFFF;
    private int mUnSelectedColor = Color.BLACK;
    private CycleView mCycleView;

    public PageIndicator(Context context) {
        super(context);
        init();
    }

    public PageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLineWidth = dip2px(14);
        mStrokeWidth = dip2px(4);
        mPaintUnselected.setColor(mUnSelectedColor);
        mPaintSelected.setStrokeWidth(mStrokeWidth);
        mPaintSelected.setColor(mSelectedColor);
        mPaintUnselected.setStrokeWidth(mStrokeWidth);
    }

    /**
     * 在CycleView设置数据之后调用
     *
     * @param cycleView
     */
    public void setCycleView(CycleView cycleView) {
        if (cycleView == null) return;
        this.mCycleView = cycleView;
        calculateValue();
        mCycleView.setOnPageChangeListener(new CycleView.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                if (mCurrentPage >= 0)
                    invalidate();
            }
        });
    }

    private void calculateValue() {
        mTotalPage = mCycleView.getAdapter().getItemRealCount();
        int maxWidth = dip2px(200);
        if (mTotalPage * mLineWidth > maxWidth) {
            mLineWidth = maxWidth / mTotalPage;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCycleView == null || mCycleView.getAdapter() == null) return;
        if (mTotalPage == 0) return;
        for (int i = 0; i < mTotalPage; i++) {
            float dx1 = i * mLineWidth;
            float dx2 = dx1 + mLineWidth;
            canvas.drawLine(dx1, 0, dx2, 0, (i == mCurrentPage) ? mPaintSelected : mPaintUnselected);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mTotalPage * mLineWidth, mStrokeWidth);
    }

    private int dip2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}


