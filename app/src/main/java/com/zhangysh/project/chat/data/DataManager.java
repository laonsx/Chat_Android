package com.zhangysh.project.chat.data;


import com.zhangysh.project.chat.inter.CallObject;

/**
 * Created by zys on 2016/9/9 0009.
 */
public class DataManager implements CallObject {
    public static Object callObject(int tag) {
        switch (tag)
        {
            case NULL:
                return null;
            case USERORDERSTATUSMANAGER:
                return null;
            case MASTERORDERSTATUSMANAGER:
                return null;
        }
        return null;
    }
}
