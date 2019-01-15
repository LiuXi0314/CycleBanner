package com.liuxi.cyclebanner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxi
 * @Email xiaoxixizhizhi@gmail.com
 */
public class CycleView extends RecyclerView {
    private static final int INVALID_POINTER = -1;
    private static final float FLING_SCALE_DOWN_FACTOR = 0.5f; // 减速因子
    private static final int FLING_MAX_VELOCITY = 8000; // 最大顺时滑动速度
    private static boolean mEnableLimitVelocity = true; // 最大顺时滑动速度
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private OnPageChangeListener mOnPageChangeListener;
    private int mCurrentPage = INVALID_POINTER;

    public CycleView(Context context) {
        super(context);
    }

    public CycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static boolean ismEnableLimitVelocity() {
        return mEnableLimitVelocity;
    }

    public static void setEnableLimitVelocity(boolean mEnableLimitVelocity) {
        mEnableLimitVelocity = mEnableLimitVelocity;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setAdapter(@Nullable CycleAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Deprecated
    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
    }

    @Nullable
    @Override
    public CycleAdapter getAdapter() {
        return super.getAdapter() instanceof CycleAdapter ? ((CycleAdapter) super.getAdapter()) : null;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (mEnableLimitVelocity) {
            velocityX = solveVelocity(velocityX);
            velocityY = solveVelocity(velocityY);
        }
        return super.fling(velocityX, velocityY);
    }

    private int solveVelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, FLING_MAX_VELOCITY);
        } else {
            return Math.max(velocity, -FLING_MAX_VELOCITY);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners == null) {
            mOnPageChangeListeners = new ArrayList<>();
        }
        mOnPageChangeListeners.add(listener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.remove(listener);
        }
    }

    public void clearOnPageChangeListeners() {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.clear();
        }
    }


    public interface OnPageChangeListener {
        void onPageSelected(int position);
    }

    public void dispatchOnPageSelected(int position) {
        mCurrentPage = position;
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        if (mOnPageChangeListeners != null) {
            for (int i = 0, z = mOnPageChangeListeners.size(); i < z; i++) {
                OnPageChangeListener listener = mOnPageChangeListeners.get(i);
                if (listener != null) {
                    listener.onPageSelected(position);
                }
            }
        }
    }


}
