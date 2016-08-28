package com.study.instrument;

import com.study.instrument.agent.ShowMessage;

/**
 * Created by ke.wu on 2016/8/27.
 */
public class Worker {

	static {
		System.out.println("Class Worker is loaded");
	}
	
	@ShowMessage
    public void doSth() {
        System.out.println("worker do something");
    }
}
