package com.laishihui.mybatispluse.demo.util;

import java.lang.reflect.Array;

/**
 * Create by tachai on 2020-08-31 08:31
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class test {


    public static void main(String[] args) {




        int[] a={2,4,2,1,9,23,32};
        System.out.println(a.length);
        a= (int[]) goodCopy(a,20);
        System.out.println(a.length);


        System.out.println(100==100);
        System.out.println(1000==1000);


    }
    // 公共复制数组的方法

    public static Object goodCopy(Object a,int newLength){
        Class cl = a.getClass();
        if(!cl.isArray())return null;
        Class componentType=cl.getComponentType();
        int length =Array.getLength(a);
        //  Array.copyOf(a,2*a.length)
        Object newArrray = Array.newInstance(componentType,newLength);
        System.arraycopy(a,0,newArrray,0,Math.min(length,newLength));
        return newArrray;
    }

}
