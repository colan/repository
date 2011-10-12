package org.sample.codec;

public interface MsgFactory {
	public abstract Class<?> getClass(int binMsgCode);
}
