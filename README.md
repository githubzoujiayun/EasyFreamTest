# EasyFreamTest
##介绍
>基于ButterKnife Retrofit RxJava oKHTTP，合理使用，并且自己定义一些机制，比如，断网机制，回调机制,CALBACK机制

##配置
>  1.在build.gradle(Project)里配置
```gradle
dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'com.jakewharton:butterknife-gradle-plugin:8.5.1'
    }
```

>  2.在build.gradle(Module)里配置
```gradle
dependencies {
    compile 'com.android.support:appcompat-v7:26.+'
    compile 'com.xiaolei:easyfreamwork:1.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'
}
```

##使用到的库
```gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:multidex:1.0.1'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-scalars:2.0.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0'
    compile 'com.google.code.gson:gson:2.7'
    compile 'com.jakewharton:butterknife:8.5.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
}
```
