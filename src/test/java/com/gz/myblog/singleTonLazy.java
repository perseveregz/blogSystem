package com.gz.myblog;

/**
 * 单例模式的懒汉式
 * @Auther:Mr.Guo
 * @create:2023/7/25-16:40
 * @VERSON:1.8
 */
public class singleTonLazy {

    //1.私有化类的构造器
    private singleTonLazy(){
    }

    //2.创建对象私有化
    private static singleTonLazy singleTonInstance;

    //3.静态方法返回
    public static singleTonLazy getInstance(){
        if (null == singleTonInstance){
            singleTonInstance = new singleTonLazy();
        }
        return singleTonInstance;
    }

}

 class singleTon{

    private singleTon(){
    }

    //为实例加上volatile关键字
    private volatile static singleTon singleTonInstance;

    public static singleTon getSingleTonInstance(){
        if(null == singleTonInstance){
            //加上synchronize锁
            synchronized(singleTon.class){
                singleTonInstance = new singleTon();
            }
        }
        return singleTonInstance;
    }
}