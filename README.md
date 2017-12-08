# EasyFreamTest
##介绍
>基于ButterKnife Retrofit RxJava oKHTTP EventBus，合理使用，并且自己定义一些机制，比如，断网机制，回调机制,CALBACK机制
消息机制，刷新机制，登录机制，cookie支持，RXJAVA与Retrofit.Call完美兼容，注解式刷新机制。注解式回调机制。

##配置


>  2.在build.gradle(Module)里配置
```gradle
dependencies {
    compile 'com.xiaolei:easyfreamwork:1.3.5'
}
```
>  3.在mainfest.xml中新增权限
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
>  4.自定义Application
```java
public class APP extends Application implements IApp
{
    List<Activity> activities = new ArrayList<>();
    @Override
    public void onCreate()
    {
        Config config = new Config();//配置
        config.setDEBUG(true);//设置DEBUG为true，便于查看日志信息
        config.setBaseUrl("http://www.baidu.com/");//设置网络请求域名
        Map<String, String> head = new HashMap<>();//设置每次网络请求都必须携带的请求头
        head.put("from", "xiaolei");
        config.setNetHeadeMap(head);//设置请求头进去
        ApplicationBreage.getInstance().initApplication(this, config);//初始化框架
        RegisteTable.getInstance().regist(String.class, StringRegist.class);//注册网络Bean，和Bean相对应的回调
        super.onCreate();
    }

    @Override
    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    @Override
    public void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    @Override
    public void removeAllActivity()
    {
        for (Activity activity : activities)
        {
            activity.finish();
        }
    }

    @Override
    public Application getApplication()
    {
        return this;
    }

    @Override
    public Retrofit getRetrofit()
    {
        return BaseNetCore.getInstance().getRetrofit();
    }

    @Override
    public CookieStore getCookieStore()
    {
        return null;
    }

    @Override
    public Context getContext()
    {
        return this;
    }
    
    /**
    * 自定义的网络的路由跳转回调
    */
    public static class StringRegist extends Regist<String>
    {
        @Override
        public String filter(String s)
        {
            return s;
        }
        @OnCallBack("/aaaa")
        public void haveToLogin(Context context, SICallBack callBack)
        {
            Log.e("haveToLogin", "haveToLogin");
        }
    }
}

```

##介绍

>#####网络请求
> 这里需要一些Retrofit的知识
>```java
>BaseRetrofit.create(Baidu.class);
>```
>#####刷新机制，比如登录，退出登录，或者什么什么情况下，需要做什么事情
>这里用的是EventBus来实现的,只需要重写这个方法
>```java
>public void onEvent(android.os.Message message)
>```
>发送消息
>```java
>BaseActivity.post(message1);
>```

>
> 网络请求路由回调，<br/>
> 我们网络请求，可能需要根据网络返回的数据自动进行路由的跳转<br/>
> 那么可以使用 继承 Regist<T> <br/>
> filter方法就是过滤的数据，@onCallBack()则是实际的过滤条件<br/>
> 下面的例子就是，如果filter里返回的数据，如果刚好对上了onCallBack里定义的字符串，<br/>
> 那么，这个方法就会被自动调用。
> 示例如下：<br/>
>
```java
    
public class StringRegist extends Regist<String>
{
    @Override
    public String filter(String s)
    {
        return s;
    }

    @OnCallBack("/aaaa")
    public void haveToLogin(Context context, SICallBack callBack)
    {
        Log.e("haveToLogin", "haveToLogin");
        Alert(context, "路由回调？？", new Action()
        {
            @Override
            public void action()
            {
                
            }
        });
    }
}
    
```

###如何使用这个网络回调路由呢？
> 在APPlication初始化的时候，在这里注册响应的对象，以及相应对象的路由注册器
```java

com.xiaolei.easyfreamwork.Config.regist(String.class, StringRegist.class);

```

--------------------------------

>新增自定义统一的网络错误提示，步骤
>
>1.新建一个类，实现类：IUnifiedFailEvent 
>
>2.在config.setUnifiedFailEventKlass(FailEvent.class);
>
>内部会自动对这个class进行实例化，并且保证唯一，当然，你不设置这个也是可以的，因为我在内部设置了一个默认的：
>
>UnifiedFailEventKlass = DefaultUnifiedFailEvent.class;
>
>修改网络请求框架，当activity关闭时，延时的网络请求之后的回调都不会执行，避免发生不必要的异常，可以在框架外部定义统一的网络请求出错处理方式，如果调用处重写onFail并且不重写super.onFail，那么将只会执行自定义的处理方式而不会执行统一的处理方式
>

#2017年12月09日02:57:11
更新日志：
    新增了、缓存网络请求的结果 [CharSequenceCacheIntercepter.java](https://github.com/xiaolei123/EasyFreamTest/blob/master/easyfreamwork/src/main/java/com/xiaolei/easyfreamwork/network/interceptor/CharSequenceCacheIntercepter.java "缓存关键类")

    支持：POST GET

>使用方法：

```java
@Headers(CacheHeaders.PRIVATE)
@FormUrlEncoded
@POST("geocoding")
Observable<DTBean> getIndex(@Field("a") String a);
```

只需要在你的Header里加上[CacheHeaders.java](https://github.com/xiaolei123/EasyFreamTest/blob/master/easyfreamwork/src/main/java/com/xiaolei/easyfreamwork/network/Catch/CacheHeaders.java "缓存标识类")
```java
/**
 * 所有请求的缓存头
 * Created by xiaolei on 2017/12/9.
 */

public class CacheHeaders
{
    // 自己设置的一个标签
    public static final String NORMAL = "cache:true";
    // 客户端可以缓存
    public static final String PRIVATE = "Cache-Control:private";
    // 客户端和代理服务器都可缓存（前端的同学，可以认为public和private是一样的）
    public static final String MAX_AGE = "Cache-Control:max-age=xxx";
    // 缓存的内容将在 xxx 秒后失效
    public static final String NO_CACHE = "Cache-Control:no-cache";
    // 需要使用对比缓存来验证缓存数据（后面介绍）
    public static final String PUBLIC = "Cache-Control:public";
    // 所有内容都不会缓存，强制缓存，对比缓存都不会触发（对于前端开发来说，缓存越多越好，so...基本上和它说886）
    public static final String NO_STORE = "Cache-Control:no-store";
}
```

就可以实现缓存效果，
#注意：只有在上次请求成功之后，才会缓存上次成功的结果，失败的结果，则不进行缓存。