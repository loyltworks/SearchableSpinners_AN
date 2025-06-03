package com.srinath.searchabledropdown;

import android.os.SystemClock;

public class BlockMultipleClick {
    private static long mLastClickTime = 0;

    public static boolean click() {
        // Prevent mis-clicks within 1000 ms
        long currentTime = SystemClock.elapsedRealtime();
        if (currentTime - mLastClickTime < 1000) {
            return true;
        }
        mLastClickTime = currentTime;
        return false;
    }
}
