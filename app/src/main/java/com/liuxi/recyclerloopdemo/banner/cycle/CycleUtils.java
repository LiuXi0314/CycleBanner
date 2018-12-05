package com.liuxi.recyclerloopdemo.banner.cycle;

import android.content.Context;

/**
 * @Author liuxi
 * @Email xiaoxixizhizhi@gmail.com
 */
public class CycleUtils {
    public static int dip2px(Context context, float f) {
        return (int) (f * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
