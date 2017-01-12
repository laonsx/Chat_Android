package com.zhangysh.project.chat.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import com.zhangysh.project.chat.App;
import com.zhangysh.project.chat.inter.NetInter;
import com.zhangysh.project.chat.util.AutoLayoutUtils;
import com.zhangysh.project.chat.util.LogUtil;

/**
 * Created by zys on 2016/7/29 0029.
 */
public abstract class BaseActivity extends AppCompatActivity implements NetInter{

    @Override
    public void onCreate(Bundle save){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(save);
       try {
           App.mApp.setCurrentActivityContext(this);
           AutoLayoutUtils.setSize(this, true, 1080, 1920);
           initView();
           getSave(save);
       }catch (Exception e){
            LogUtil.e("BaseActivity.init()———>exception:"+e.toString());
       }
    }
//    public HandlerUtils utils;
//    public RxPermissions rxPermissions;
    private void initView() {
        setContentView();
        AutoLayoutUtils.auto(this);
        findViewById();
        setListener();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    protected void getSave(Bundle save){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        App.mApp.setCurrentActivityContext(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public abstract void  setContentView();
    public abstract void findViewById();
    public abstract void setListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

