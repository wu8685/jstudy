package com.study.instrument.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by ke.wu on 2016/8/27.
 */
public class MyAgent {

	// Called by JVM before class loading
    public static void premain(String args, Instrumentation inst) {
    	System.out.println("Tramsformer is registerred to JVM");
    	inst.addTransformer(new MyTransformer());
    }
}
