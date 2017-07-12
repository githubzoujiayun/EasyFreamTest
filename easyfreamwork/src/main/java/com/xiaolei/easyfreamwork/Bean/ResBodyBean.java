package com.xiaolei.easyfreamwork.Bean;


/**
 * Created by xiaolei on 2017/3/10.
 */
public class ResBodyBean<T>
{
    /**
     * status : OK
     * message : 处理成功或失败所对应的提示信息
     * url : 需要后续跳转的URL
     * callback : 需要后续执行的操作名称
     * content : 请求所需的接口输出数据
     * remark : 备注信息
     */
    public String status = "";
    public String message = "";
    public String url = "";
    public String callback = "";
    public T content;
    public String remark = "";
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getCallback()
    {
        return callback;
    }

    public void setCallback(String callback)
    {
        this.callback = callback;
    }

    public T getContent()
    {
        return content;
    }

    public void setContent(T content)
    {
        this.content = content;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public boolean isOK()
    {
        return "OK".equals(this.status);
    }
}
