package com.zhangysh.project.chat.data;


import java.util.HashMap;
import java.util.Map;

public class DataCenter
{
    private Map dataManager= new HashMap();

    private Map userManager=new HashMap();

    private Map orderManager=new HashMap();

    private Map statusManager=new HashMap();

    public void init()
    {

    }

    private volatile static DataCenter	mInstance;

    private DataCenter()
    {
        init();
    }

    public static DataCenter getInstance()
    {
        if ( null == mInstance )
        {
            synchronized ( DataCenter.class )
            {
                if ( null == mInstance )
                    mInstance = new DataCenter();
            }
        }
        return mInstance;
    }

    public Map getDataManager(){
        return  dataManager;
    }

    public Map getUserManager(){
        return  userManager;
    }

    public Map getOrderManager(){
        return  orderManager;
    }

    public Map getStatusManager(){
        return  statusManager;
    }

    public void deleteUserManager(){
        userManager=new HashMap();
    }

    public String getInfo(){
        return "dataManager:::"+dataManager.toString()
                +"-----userManager:::"+userManager.toString()
                +"-----orderManager:::"+orderManager.toString()
                +"-----statusManager:::"+statusManager.toString();
    }
}

