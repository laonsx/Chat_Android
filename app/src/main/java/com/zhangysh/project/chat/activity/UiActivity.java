package com.zhangysh.project.chat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangysh.project.chat.R;
import com.zhangysh.project.chat.activity.base.BaseUiActivity;
import com.zhangysh.project.chat.fragment.GlobalChatFragment;
import com.zhangysh.project.chat.fragment.GroupChatFragment;
import com.zhangysh.project.chat.fragment.MyFragment;
import com.zhangysh.project.chat.fragment.base.BaseFragement;
import com.zhangysh.project.chat.util.LogUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class UiActivity extends BaseUiActivity {
    @Bind(R.id.titlebarcenter)
    TextView titlebarcenter;
    @Bind(R.id.shouyeicon)
    ImageView shouyeicon;
    @Bind(R.id.shouyetext)
    TextView shouyetext;
    @Bind(R.id.dingdanicon)
    ImageView dingdanicon;
    @Bind(R.id.dingdantext)
    TextView dingdantext;
    @Bind(R.id.dingdan)
    LinearLayout dingdan;
    @Bind(R.id.kefuicon)
    ImageView kefuicon;
    @Bind(R.id.kefutext)
    TextView kefutext;
    @Bind(R.id.wodeicon)
    ImageView wodeicon;
    @Bind(R.id.wodetext)
    TextView wodetext;
    @Bind(R.id.titlebarright)
    TextView titlebarright;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private BaseFragement mFragment;
    private GlobalChatFragment mStartFragment;
    private GlobalChatFragment mMasterOrderFragment;
    private GlobalChatFragment mUserOrderFragment;
    private GroupChatFragment shifudingdan;
    private BaseFragement mOrderChangeFragment;
    private MyFragment mMyFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.ui_activity);
    }

    @Override
    public void findViewById() {
        mManager = getSupportFragmentManager();
        onClick(dingdan);
    }

    @Override
    public void setListener() {
        titlebarright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTransaction = mManager.beginTransaction();
                mTransaction.hide(mFragment);
                mType++;
                getOrderFragment(mType % 2);
                if (mType == 1) {
                    mTransaction.add(R.id.fragment, mOrderChangeFragment);
                } else {
                    mTransaction.show(mOrderChangeFragment);
                }
                mFragment = mOrderChangeFragment;
                mTransaction.commit();
            }
        });
    }

    private int mType = 0;

    private BaseFragement getOrderFragment(int type) {
        switch (type) {
            case 0:
                if (mUserOrderFragment == null) {
                    mUserOrderFragment = new GlobalChatFragment();
                }
                mOrderChangeFragment = mUserOrderFragment;
                titlebarright.setText("个人");
                return mOrderChangeFragment;
            case 1:
                if (shifudingdan == null) {
                    shifudingdan = new GroupChatFragment();
                }

            default:
                mOrderChangeFragment = shifudingdan;
                titlebarright.setText("群组");
                return mOrderChangeFragment;
        }
    }

    @OnClick({R.id.shouye, R.id.dingdan, R.id.kefu, R.id.wode})
    public void onClick(View view) {
        mTransaction = mManager.beginTransaction();
        startStatus();
        if (mFragment != null) {
            mTransaction.hide(mFragment);
        }
        switch (view.getId()) {
            case R.id.shouye:
                titlebarcenter.setText("首页");
//                titlebarcenter.setText("");
//                titlebarcenter.setBackgroundResource(R.mipmap.yixiuguanjia);
                titlebarright.setVisibility(View.GONE);
                if (mStartFragment == null) {
                    mStartFragment = new GlobalChatFragment();
                    mFragment = mStartFragment;
                    mTransaction.add(R.id.fragment, mStartFragment, "shouye");
                } else if (mFragment != mStartFragment) {
                    mTransaction.hide(mFragment);
                    mTransaction.show(mStartFragment);
                    mFragment = mStartFragment;
                } else {
                    mTransaction.show(mFragment);
                }
                shouyeicon.setSelected(true);
                shouyetext.setSelected(true);
                break;
            case R.id.dingdan:
                titlebarcenter.setText("列表");
//                titlebarcenter.setBackgroundResource(R.color.interlayer);
                titlebarright.setVisibility(View.VISIBLE);
                if (mOrderChangeFragment == null) {
                    mOrderChangeFragment = getOrderFragment(mType % 2);
                    mFragment = mOrderChangeFragment;
                    mTransaction.add(R.id.fragment, mOrderChangeFragment, "dingdan");
                } else if (mFragment != mOrderChangeFragment) {
                    mTransaction.hide(mFragment);
                    mTransaction.show(mOrderChangeFragment);
                    mFragment = mOrderChangeFragment;
                } else {
                    mTransaction.show(mFragment);
                }
                dingdanicon.setSelected(true);
                dingdantext.setSelected(true);
                break;
            case R.id.kefu:
                titlebarcenter.setText("客服");
//                titlebarcenter.setBackgroundResource(R.color.interlayer);
                titlebarright.setVisibility(View.GONE);
                if (mMasterOrderFragment == null) {
                    mMasterOrderFragment = new GlobalChatFragment();
                    mFragment = mMasterOrderFragment;
                    mTransaction.add(R.id.fragment, mMasterOrderFragment, "wode");
                } else if (mFragment != mMasterOrderFragment) {
                    mTransaction.hide(mMasterOrderFragment);
                    mTransaction.show(mMasterOrderFragment);
                    mFragment = mMasterOrderFragment;
                } else {
                    mTransaction.show(mFragment);
                }
                kefuicon.setSelected(true);
                kefutext.setSelected(true);
                break;
            case R.id.wode:
                titlebarcenter.setText("我的");
//                titlebarcenter.setBackgroundResource(R.color.interlayer);
                titlebarright.setVisibility(View.GONE);
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    mFragment = mMyFragment;
                    mTransaction.add(R.id.fragment, mMyFragment, "wode");
                } else if (mFragment != mMyFragment) {
                    mTransaction.hide(mFragment);
                    mTransaction.show(mMyFragment);
                    mFragment = mMyFragment;
                } else {
                    mTransaction.show(mFragment);
                }
                wodeicon.setSelected(true);
                wodetext.setSelected(true);
                break;
        }
        mTransaction.commit();
    }

    private void startStatus() {
        shouyeicon.setSelected(false);
        shouyetext.setSelected(false);
        dingdanicon.setSelected(false);
        dingdantext.setSelected(false);
        kefuicon.setSelected(false);
        kefutext.setSelected(false);
        wodeicon.setSelected(false);
        wodetext.setSelected(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }
}
