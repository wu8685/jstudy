package com.study.instrument;

import javassist.ClassPool;;

/**
 * Created by ke.wu on 2016/8/27.
 */
public class Framework {

    public static void main(String[] args) {
    	System.out.println("run Framework.main");
        Worker w = new Worker();
        w.doSth();
        
        ClassPool.getDefault();
    }

}
