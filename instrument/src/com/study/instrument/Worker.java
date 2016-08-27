package com.study.instrument;

import com.study.instrument.agent.ShowMessage;

/**
 * Created by ke.wu on 2016/8/27.
 */
public class Worker {

	public int value;
	
	@ShowMessage
    public void doSth() {
        System.out.println("worker do something");
    }
}
