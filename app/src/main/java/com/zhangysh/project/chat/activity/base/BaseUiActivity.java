package com.zhangysh.project.chat.activity.base;

import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangysh.project.chat.R;
import com.zhangysh.project.chat.util.LogUtil;


/**
 * Created by Administrator on 2016/10/21 0021.
 */
public abstract class BaseUiActivity extends BaseActivity {

    public void onEndActivity(View view){
        this.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        LogUtil.i("Key_Stuta = " + event.getAction());
        if (keycode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 右键处理
            this.finish();
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        return true;
    }

}
