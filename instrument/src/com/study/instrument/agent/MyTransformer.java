package com.study.instrument.agent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;

/**
 * Created by ke.wu on 2016/8/27.
 */
public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
    	System.out.println("Transformer is processing class " + className);
		ClassPool cp = ClassPool.getDefault();
		CtClass clazz = null;
		byte[] transformedBuffer = classfileBuffer;
		try {
			clazz = cp.makeClass(new ByteArrayInputStream(classfileBuffer));
			if (! clazz.isInterface()) {
				CtBehavior[] methods = clazz.getDeclaredBehaviors();
				for (CtBehavior m : methods) {
					Object[] annos = m.getAnnotations();
					for (Object a : annos) {
						if (a instanceof ShowMessage) {
							wrap(m);
							transformedBuffer = clazz.toBytecode();
							break;
						}
					}
				}
			}
		} catch (IOException | RuntimeException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
		
        return transformedBuffer;
    }
    
    private void wrap(CtBehavior method) throws CannotCompileException {
    	method.insertBefore("System.out.println(\"before method: " + method.getName() + "\");");
    	method.insertAfter("System.out.println(\"after method: " + method.getName() + "\");");
    }
}

class Test {
	
	@ShowMessage
	public void test() {
		
	}
}
