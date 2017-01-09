package com.zhangysh.project.chat.util;

import android.widget.Toast;

import com.zhangysh.project.chat.App;

public class ToastUtil {
    private ToastUtil() {

    }

    private static Toast mToast;

    public static void show(int resId) {

        show(App.getContext().getString(resId));
    }

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
