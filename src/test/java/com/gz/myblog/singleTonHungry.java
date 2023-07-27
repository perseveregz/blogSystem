package com.gz.myblog;

/**
 * 单例模式之饿汉式
 * @Auther:Mr.Guo
 * @create:2023/7/25-16:40
 * @VERSON:1.8
 */
public class singleTonHungry {

    //1.私有化类的构造器
    private singleTonHungry(){
    }

    //2.内部创建类的对象
    private static singleTonHungry singleTonHungry = new singleTonHungry();

    //3.提供静态方法返回
    private static singleTonHungry getSingleTonHungry(){
        return singleTonHungry;
    }

}
