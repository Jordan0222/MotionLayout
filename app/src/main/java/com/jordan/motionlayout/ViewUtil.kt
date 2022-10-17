package com.jordan.motionlayout

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.WindowMetrics

class ViewUtil {
    companion object {
        private var application: Application? = null

        private fun getApplication(): Application {
            if (application == null) {
                throw IllegalStateException("You must call init() method at first!")
            }
            return application as Application
        }

        fun init(app: Application) {
            ViewUtil.application = app
        }

        fun getScreenWidth(): Int {
            val windowManager: WindowManager = getApplication().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
                windowMetrics.bounds.width();
            } else {
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics);
                displayMetrics.widthPixels;
            }
        }
    }
}