package com.xiaolei.easyfreamwork.eventbus;

/**
 * Event所发送的消息
 * Created by xiaolei on 2017/7/13.
 */

public abstract class Message
{
    public class TYPE
    {
        public static final String FRESH = "FRESH";
        public static final String LOGIN = "LOGIN";
        public static final String LOGOUT = "LOGOUT";
    }
    public String Type = "";
}
