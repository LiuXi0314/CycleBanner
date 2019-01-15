package com.liuxi.cyclebanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 无限循环banner
 *
 * @Author liuxi
 * @Email xiaoxixizhizhi@gmail.com
 */
public class CycleBanner extends FrameLayout {

    public CycleBanner(@NonNull Context context) {
        super(context);
    }

    public CycleBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CycleBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {

    }

}
