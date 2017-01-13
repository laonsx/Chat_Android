package com.zhangysh.project.chat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangysh.project.chat.R;
import com.zhangysh.project.chat.fragment.base.BaseFragement;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
public class RegistFragment extends BaseFragement {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.regist_fragment,null);
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void saveBundle(Bundle outState) {

    }

    @Override
    protected void getSavedBundle(Bundle savedInstanceState) {

    }
}
