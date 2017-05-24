package com.zhangysh.project.chat.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangysh.project.chat.util.LogUtil;


public abstract class BaseFragement extends Fragment{
    protected Context mContext;
    public BaseFragement(){}
    protected View view=null;
    /**
     * 当前fragment是否显示
     */
    protected boolean isVisible;
    /**
     * 是否整备加载数据
     */
    protected boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            mContext=getActivity();
            view=createView(inflater,container,savedInstanceState);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSavedBundle(savedInstanceState);

        try {
            init(view);

            isPrepared = true;
            onVisible();

        } catch (Exception e) {
            LogUtil.e("BaseFragment======------======------======error"+e);
            e.printStackTrace();
        }

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveBundle(outState);
    }

    /**
     * Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        if(!isPrepared || !isVisible) {
            return;
        }
        LogUtil.e("fragment is lazyloading data");
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(view!=null&&view.getParent()!=null){
            ViewGroup parent=(ViewGroup)view.getParent();
            parent.removeView(view);
        }
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化空间
     * @param view
     */
    protected abstract void init(View view);
   
    /**
     * 加载数据，执行懒加载
     */
    protected abstract void lazyLoad();

    /**
     * 保存页面状态
     */
    protected abstract void saveBundle(Bundle outState);

    /**
     * 获取之前页面状态
     */
    protected abstract void getSavedBundle(Bundle savedInstanceState);

    protected void onInvisible(){}

}
