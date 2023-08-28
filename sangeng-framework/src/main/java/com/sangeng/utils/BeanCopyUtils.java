package com.sangeng.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    //私有的无参构造方法
    private BeanCopyUtils() {}
    //bean拷贝对象
    public static <V> V copyBean(Object source,Class<V> clazz){
        V res = null;
        try {
             res = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source,res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    //bean拷贝集合
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                //调用上面的copyBean方法
                .map(o -> copyBean(o,clazz))
                //将字节流转化为List对象
                .collect(Collectors.toList());
    }
}
